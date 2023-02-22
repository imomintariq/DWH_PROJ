package DWHEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Entity
@Table(name = "date")
public class Date {
    @Id
    @Column(name = "DATE_ID", nullable = false, length = 8)
    private String id;

    @Column(name = "DD")
    private Integer dd;

    @Column(name = "MM")
    private Integer mm;

    @Column(name = "QTR")
    private Integer qtr;

    @Column(name = "YYYY")
    private Integer yyyy;

    @Column(name = "WEEK_OF_YEAR")
    private Integer weekOfYear;

    @Column(name = "WEEKDAY", length = 15)
    private String weekday;


    public Date(String time_id, LocalDate t_date) {
        this.setId(time_id);
        this.setDd(t_date.getDayOfMonth());
        this.setMm(t_date.getMonthValue());
        int month = t_date.getMonthValue();
        int quater = 0;
        if (month <= 3) {
            quater = 1;
        } else if (month <= 6) {
            quater = 2;
        } else if (month <= 9) {
            quater = 3;
        } else if (month <= 12) {
            quater = 4;
        }

        this.setQtr(quater);
        this.setYyyy(t_date.getYear());

        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = t_date.get(woy);

        this.setWeekOfYear(weekNumber);
        this.setWeekday(t_date.getDayOfWeek().toString());
    }

    public Date() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDd() {
        return dd;
    }

    public void setDd(Integer dd) {
        this.dd = dd;
    }

    public Integer getMm() {
        return mm;
    }

    public void setMm(Integer mm) {
        this.mm = mm;
    }

    public Integer getQtr() {
        return qtr;
    }

    public void setQtr(Integer qtr) {
        this.qtr = qtr;
    }

    public Integer getYyyy() {
        return yyyy;
    }

    public void setYyyy(Integer yyyy) {
        this.yyyy = yyyy;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

}