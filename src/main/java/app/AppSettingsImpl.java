/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.util.MyCalendar;
import org.springframework.stereotype.Component;

/**
 *
 * @author NavNag
 */
@Component
public class AppSettingsImpl implements AppSettings {

    private int slotDuration = 2 * 60; // Default 2 Hours
    private int itemsPerVehicle = 20; // Default 20 items per vehicle

    public static enum AvailableSlots {

        SLOT1(9, 11),
        SLOT2(11, 13),
        SLOT3(14, 16),
        SLOT4(16, 18);
        private int start;
        private int end;

        private AvailableSlots(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Slot{" + "start=" + start + ", end=" + end + '}';
        }

        /**
         * returns the slot is the time frame is between the provided slot
         * timings
         *
         * @param cal
         * @return AvailableSlots or null
         */
        public static AvailableSlots getByTime(MyCalendar cal) {
            int hours = cal.getHours();
            int mins = cal.getMinutes();
            for (AvailableSlots slot : values()) {
                if ((slot.start <= hours || hours == 13) && (slot.end > hours || (slot.end == hours && mins == 0))) {
                    return slot;
                }
            }
            return null;
        }

    }

    @Override
    public int getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    @Override
    public int getItemsPerVehicle() {
        return itemsPerVehicle;
    }

    public void setItemsPerVehicle(int itemsPerVehicle) {
        this.itemsPerVehicle = itemsPerVehicle;
    }

    @Override
    public boolean isClosedForTheDay() {
        MyCalendar cal = new MyCalendar().moveToStartTheOfDay();
        int hours = cal.getHours();
        int mins = cal.getMinutes();
        int eod = AvailableSlots.SLOT4.getEnd();
        return (hours > eod || (hours == eod && mins == 0));
    }

}
