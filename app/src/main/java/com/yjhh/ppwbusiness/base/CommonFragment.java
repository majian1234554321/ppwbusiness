package com.yjhh.ppwbusiness.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.view.*;
import me.yokeyword.fragmentation.SupportFragment;

import java.util.ArrayList;
import java.util.List;

/**

 */
public abstract class CommonFragment extends SupportFragment {

	protected Context context;

	protected List<Call> callList;

	private Handler handler;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(getLayoutId(), null);
		context = getActivity();
		callList = new ArrayList<>();



		//setEvent(view);

		return view;
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		init(view, savedInstanceState);
	}

	/**
	 * 返回当前fragment需要引用的布局Id
	 */
	public abstract int getLayoutId();

	public abstract void init(View view, Bundle savedInstanceState);






	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
	}
}
