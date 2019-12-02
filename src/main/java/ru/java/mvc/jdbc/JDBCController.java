package ru.java.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.java.mvc.bean.Guest;

import java.util.List;

@Controller
public class JDBCController {

    @Autowired JDBCExample jdbcExample;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView jdbcSelectAllGuests() {
        List<Guest> guests =  jdbcExample.queryAllGuests();
        return new ModelAndView("index", "resultObject", guests);
    }

    @RequestMapping(value = "/jdbcInsertGuest", method = RequestMethod.POST)
    public ModelAndView jdbcInsertGuest(@ModelAttribute("guest") Guest guest) {
        jdbcExample.insertGuest(guest);
        return jdbcSelectAllGuests();
    }
}
