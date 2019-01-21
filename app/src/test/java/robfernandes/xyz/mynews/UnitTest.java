package robfernandes.xyz.mynews;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static robfernandes.xyz.mynews.storage.local.DataManager.formatDateToCallAPI;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    @Test
    public void doesFormatDateToCallAPI() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 12);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.YEAR, 2019);

        String formattedDate = formatDateToCallAPI(calendar);
        assertEquals("20190812", formattedDate);
    }
}