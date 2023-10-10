package com.moises.os.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moises.os.domain.enuns.Priority;
import com.moises.os.domain.enuns.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime openingDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime closureDate;

	private Integer priority;
	private String comments;
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public OS() {
		super();
		this.setOpeningDate(LocalDateTime.now());
		this.setPriority(Priority.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public OS(Integer id, Priority priority, String comments, Status status, Technician technician, Client client) {
		super();
		this.id = id;
		this.setOpeningDate(LocalDateTime.now());
		this.priority = (priority == null) ? 0 : priority.getCod();
		this.comments = comments;
		this.status = (status == null) ? 0 : status.getCod();
		this.technician = technician;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDateTime getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(LocalDateTime closureDate) {
		this.closureDate = closureDate;
	}

	public Priority getPriority() {
		return Priority.toEnum(this.priority);
	}

	public void setPriority(Priority priority) {
		this.priority = priority.getCod();
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OS other = (OS) obj;
		return Objects.equals(id, other.id);
	}

}
