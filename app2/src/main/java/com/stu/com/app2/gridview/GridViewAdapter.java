package com.stu.com.app2.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.stu.com.app2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liangjie
 * @data 2013-11-8下午11:08:39
 * @Description TODO
 */
public class GridViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	public GridViewAdapter(Context context, List<Map<String, Object>> list) {
		super();
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		Viewholder viewholder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.gridview_item, null);
			viewholder = new Viewholder(convertView);
			convertView.setTag(viewholder);
		} else {
			viewholder = (Viewholder) convertView.getTag();
		}
		viewholder.update(list.get(position),position);
		return convertView;
	}

	public class Viewholder {
		private ImageView imageView;
		private TextView textView;
		private FrameLayout layout;
		private View view;
		public Viewholder(View convertView) {
			imageView=(ImageView) convertView.findViewById(R.id.imageView);
			textView=(TextView) convertView.findViewById(R.id.textView);
			layout=(FrameLayout) convertView.findViewById(R.id.layout);
			view=convertView.findViewById(R.id.line);
		}

		public void update(Map<String, Object> map,int position) {
			textView.setText(map.get("name").toString());
			int i=0;
			i=position%3;
			switch (i) {
			case 0:
				layout.setPadding(0, 0, 20, 0);
				break;
			case 1:
				layout.setPadding(10, 0, 10, 0);
				break;
			case 2:
				layout.setPadding(20, 0, 0, 0);
				break;
			default:
				break;
			}
			String id=map.get("id").toString();
			setImage(id, imageView);
			setLine(position, view);
		}
	}
	
	private void setImage(String id,ImageView imageView){
		if(id.equals("1")){
			imageView.setImageResource(R.mipmap.ic_launcher);
		}else if(id.equals("2")){
			imageView.setImageResource(R.mipmap.ic_launcher);
		}else if(id.equals("3")){
			imageView.setImageResource(R.mipmap.ic_launcher);
		}else if(id.equals("4")){
			imageView.setImageResource(R.mipmap.ic_launcher);
		}else if(id.equals("5")){
			imageView.setImageResource(R.mipmap.ic_launcher);
		}
	}
	
	private void setLine(int position,View view){
		int i=0;
		i=list.size()%3;
		if(position+i+1>list.size()){
			view.setVisibility(View.GONE);
		}
	}
}