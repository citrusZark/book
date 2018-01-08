package org.bookpub.propertyEditor;

import java.beans.PropertyEditorSupport;

import org.bookpub.entity.Isbn;
import org.springframework.util.StringUtils;

public class IsbnEditor extends PropertyEditorSupport{
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)) {
			setValue(new Isbn(text.trim()));
		} else {
			setValue(null);
		}
		super.setAsText(text);
	}
	
	@Override
	public String getAsText() {
		Isbn isbn = (Isbn) getValue();
		if (isbn != null) {
			return isbn.getIsbn();
		} else {
			return "";
		}
	}
}
