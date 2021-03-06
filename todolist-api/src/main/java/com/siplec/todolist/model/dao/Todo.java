package com.siplec.todolist.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class Todo extends Auditor {

	private static final long serialVersionUID = 759833625132946781L;

	public Todo() {
	}

	public Todo(@NotBlank @Size(max = 80) String label, boolean done) {
		this.label = label;
		this.done = done;
	}

	private long id;

	@NotBlank
	@Size(max = 80)
	private String label;

	private boolean done;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "label", nullable = false)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "done")
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", label=" + label + ", done=" + done + "]";
	}
}
