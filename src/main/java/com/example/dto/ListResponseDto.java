package com.example.dto;


public class ListResponseDto {


	public ListResponseDto(Long count) {

		super();
		this.count = count;

	}

	private Object data;

	private Long count;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public ListResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListResponseDto(Object data, Long count) {
		super();
		this.data = data;
		this.count = count;
	}

	

}
