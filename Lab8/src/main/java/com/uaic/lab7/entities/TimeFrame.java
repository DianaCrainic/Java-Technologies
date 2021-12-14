package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "time_frames")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "TimeFrame.getAll", query = "SELECT timeFrame FROM TimeFrame timeFrame")
})
public class TimeFrame extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "start_date")
    private Date startDate;

    @Basic(optional = false)
    @Column(name = "end_date")
    private Date endDate;
}
