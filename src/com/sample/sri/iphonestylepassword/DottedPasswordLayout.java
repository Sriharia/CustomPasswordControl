package com.sample.sri.iphonestylepassword;

import com.sample.sri.iphonestyelpasswrod.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author Sri - (srihari.yachamaneni@gmail.com)
 * 
 */
public class DottedPasswordLayout extends FrameLayout implements TextWatcher,
		OnFocusChangeListener {

	private EditText edit_text;
	private LinearLayout dotLayout;
	/*
	 * Max no.of chars allowed, default is 4
	 */
	private int dottedChildCount = 4;

	public DottedPasswordLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initViews(attrs);
	}

	public DottedPasswordLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(attrs);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		togglePasswordLabels(s.length());
	}

	public void togglePasswordLabels(int length) {
		/*
		 * hide dot in every child box
		 */
		for (int i = 0; i < dotLayout.getChildCount(); i++)
			((LinearLayout) dotLayout.getChildAt(i)).getChildAt(0)
					.setVisibility(View.INVISIBLE);
		/*
		 * Loop through each child and show dot if it falls under length
		 */
		if (length <= dottedChildCount) {
			while (length > 0) {
				try {
					((LinearLayout) dotLayout.getChildAt(length - 1))
							.getChildAt(0).setVisibility(View.VISIBLE);
					length--;
				} catch (NullPointerException npe) {
					Log.e("PasswordTextWatcher", "no such view");
					return;
				}
			}
		}
	}

	/**
	 * Gives you the password text
	 * 
	 * @return
	 */
	public Editable getText() {
		if (null != edit_text)
			return edit_text.getText();
		return null;
	}

	public boolean setFocus() {
		if (null != edit_text)
			return edit_text.requestFocus();
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		/*
		 * Toggle the Background of view on Focus changed.
		 */
		if (dotLayout != null)
			for (int i = 0; i < dotLayout.getChildCount(); i++)
				dotLayout.getChildAt(i).setBackgroundResource(
						hasFocus ? R.drawable.square_focus : R.drawable.square);
	}

	/**
	 * Initiates views and adds dotted children based on max password chars
	 * 
	 * @param attrs
	 */
	private void initViews(AttributeSet attrs) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.dotted_password_layout, this, true);

		dotLayout = (LinearLayout) v.findViewById(R.id.dottedPwdLayout);
		edit_text = (EditText) v.findViewById(R.id.edit_text);
		edit_text.setOnFocusChangeListener(this);
		edit_text.addTextChangedListener(this);
		edit_text.setFocusableInTouchMode(true);

		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.DottedPasswordLayout);
		dottedChildCount = a.getInteger(
				R.styleable.DottedPasswordLayout_length, 4);
		/*
		 * set a filter to change maxlength limit for editText
		 */
		edit_text.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				dottedChildCount) });
		a.recycle();
		for (int i = 0; i < dottedChildCount; i++) {
			View child = inflater.inflate(R.layout.dotted_child, dotLayout,
					false);
			dotLayout.addView(child, dotLayout.getChildCount());
		}
	}

}
