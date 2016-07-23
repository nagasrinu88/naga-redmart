/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db.dao;

import app.db.SlotHistoryByDay;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NavNag
 */
@Repository
public interface SlotHistoryByDayDao extends CrudRepository<SlotHistoryByDay, Long> {

    public SlotHistoryByDay findByDate(Date date);
}
