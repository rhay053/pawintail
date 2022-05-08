package com.pawintail.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pawintail.dto.EventFormDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="event")
@Setter
@Getter
@SequenceGenerator(name="event_seq_gen", sequenceName = "event_seq", initialValue = 1, allocationSize = 1)
@ToString
public class Event extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "event_seq")
	@Column(name="event_id")
	private Long eventId;
	@Column(name="event_name")
	@NotBlank
	private String eventName;

	public void updateEvent(EventFormDto eventFormDto) {
		this.eventName = eventFormDto.getEventName();

	}
}
