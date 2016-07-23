/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.services;

import app.AppSettings;
import app.db.Order;
import app.db.Slot;
import app.db.SlotHistoryByDay;
import app.db.dao.OrdersDao;
import app.db.dao.SlotDao;
import app.db.dao.SlotHistoryByDayDao;
import app.util.MyCalendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author NavNag
 */
@Component
public class SlotService {

    static final Logger log = Logger.getLogger(SlotService.class.getName());

    @Autowired
    private AppSettings settings;

    @Autowired
    private SlotHistoryByDayDao slotHistoryByDayDao;
    @Autowired
    private SlotDao slotDao;
    @Autowired
    private OrdersDao ordersDao;

    /**
     *
     * @param order
     * @return
     */
    public Slot bookSlot(Order order) {
        log.log(Level.INFO, "Trying to book a slot for order {0}", order);
        boolean alloted = false;
        Slot slot = null;
        MyCalendar cal = new MyCalendar().moveToStartTheOfDay();
        if (settings.isClosedForTheDay()) { // if the day is over then moving to next day
            cal.nextDay();
            log.log(Level.INFO, "Closed for today so moving to next day {0}", cal.getDate());
        }
        do {
            Date date = cal.getDate();
            log.log(Level.INFO, "Looking for slot on {0}", date);
            SlotHistoryByDay slotHis = slotHistoryByDayDao.findByDate(date);
            System.out.println(slotHis);
            if (slotHis == null) {
                slotHis = new SlotHistoryByDay();
                slotHis.setDate(date);
                // setting availability for each vehicle
                slotHis.setV1(settings.getItemsPerVehicle());
                slotHis.setV2(settings.getItemsPerVehicle());
                slotHis.setV3(settings.getItemsPerVehicle());
                slotHis.setV4(settings.getItemsPerVehicle());
                slotHistoryByDayDao.save(slotHis);
            }
            slot = slotHis.book(order.getTotalItems());
            if (slot != null) {
                slot.setOrderId(order.getId());
                slot.setAllottedDate(date);
                slotDao.save(slot);
                slotHistoryByDayDao.save(slotHis);
                log.log(Level.INFO, "Slot booked for {0}", slot);
                alloted = true;
                break;
            }
            log.log(Level.INFO, "No slot available on {0}", date);
            cal.nextDay(); // moving to next day
        } while (!alloted);

        if (alloted) {
            order.setAllotted(true);
            ordersDao.save(order);
        }
        return slot;
    }

}
