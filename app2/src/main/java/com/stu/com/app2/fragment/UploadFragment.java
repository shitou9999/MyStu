package com.stu.com.app2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.GlideImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.stu.com.app2.R;

import java.util.ArrayList;
import java.util.List;

public class UploadFragment extends Fragment implements View.OnClickListener{
    private GridView gridView;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> mIamgeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_upload_fragment,null);
        view.findViewById(R.id.select).setOnClickListener(this);
        view.findViewById(R.id.upload).setOnClickListener(this);
        gridView = (GridView) view.findViewById(R.id.gridView);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select:
                imagePicker=ImagePicker.getInstance();
                imagePicker.setImageLoader(new GlideImageLoader());
                imagePicker.setShowCamera(false);
                imagePicker.setSelectLimit(9);
                imagePicker.setCrop(true);
                Intent intent=new Intent(getContext(), ImageGridActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.upload: //上传
//                if (images != null) {
//                    for (int i = 0; i < images.size(); i++) {
//                        MyUploadListener listener = new MyUploadListener();
//                        listener.setUserTag(gridView.getChildAt(i));
//                        UploadManager.getInstance(getContext()).addTask(Urls.URL_FORM_UPLOAD, new File(images.get(i).path), "imageFile", listener);
//                    }
//                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==ImagePicker.RESULT_CODE_ITEMS){
            if(data!=null&&requestCode==100){
                mIamgeList= (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                MyAdapter adapter=new MyAdapter(mIamgeList);

                gridView.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class MyAdapter extends BaseAdapter{
        private List<ImageItem> items;
        public MyAdapter(List<ImageItem> items){
            this.items=items;
            notifyDataSetChanged();//更新适配器
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public ImageItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            int size=gridView.getWidth()/3;
            ViewHolder holder;
            if(convertView==null){
                convertView= android.view.View.inflate(getContext(),R.layout.item_upload_manager,null);
                holder=new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            imagePicker.getImageLoader().displayImage(getActivity(), getItem(position).path,holder.imageView,size,size);
            return convertView;
        }
    }


    private class ViewHolder{
        private ImageView imageView;
        public ViewHolder(View convertView){
            imageView= (ImageView) convertView.findViewById(R.id.imageView);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, gridView.getWidth() / 3);
            imageView.setLayoutParams(params);

        }
    }


}
