package com.example.qrcodebarcodescanner;

import com.google.gson.annotations.SerializedName;

public class NotificationDataItem {

	@SerializedName("text")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}