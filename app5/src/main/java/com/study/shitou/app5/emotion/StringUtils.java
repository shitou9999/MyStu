package com.study.shitou.app5.emotion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.study.shitou.app5.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	//		这里以微博表情为例
//		String regex = "\\[[\u4e00-\u9fa5\\w]+\\]";----匹配方括号内有一或多个文字和单词字符的文本
//  最外面是方括号符号,要注意由于[]在正则中有特殊意义,
//		所以需要用斜杠\转义一下(\本身也需要转义所以是俩\\)
	//中间\u4e00-\u9fa5表示中文,\\w表示下划线的任意单词字符,+ 代表一个或者多个


//	用matcher.find获取到匹配的开始位置,作为setSpan的start值,用matcher.group方法获取到匹配规则的具体表情文字,end值则直接利用开始位置加上表情文字的长度即可
	public static SpannableString getWeiboContent(final Context context, final TextView tv, String source) {
		String regexAt = "@[\u4e00-\u9fa5\\w]+";
		String regexTopic = "#[\u4e00-\u9fa5\\w]+#";
		String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";

		
		String regex = "(" + regexAt + ")|(" + regexTopic + ")|(" + regexEmoji + ")";
		
		SpannableString spannableString = new SpannableString(source);
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(spannableString);
		
		if(matcher.find()) {
			tv.setMovementMethod(LinkMovementMethod.getInstance());
			matcher.reset();
		}
		
		while(matcher.find()) {
			final String atStr = matcher.group(1);
			final String topicStr = matcher.group(2);
			String emojiStr = matcher.group(3);
			
			if(atStr != null) {
				int start = matcher.start(1);
				
				BoreClickableSpan clickableSpan = new BoreClickableSpan(context) {
					
					@Override
					public void onClick(View widget) {
//						Intent intent = new Intent(context, UserInfoActivity.class);
//						intent.putExtra("userName", atStr.substring(1));
//						context.startActivity(intent);
					}
				};
				spannableString.setSpan(clickableSpan, start, start + atStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			
			if(topicStr != null) {
				int start = matcher.start(2);
				
				BoreClickableSpan clickableSpan = new BoreClickableSpan(context) {
					
					@Override
					public void onClick(View widget) {
//						ToastUtils.showToast(context, "话题: " + topicStr, Toast.LENGTH_SHORT);?
					}
				};
				spannableString.setSpan(clickableSpan, start, start + topicStr.length(), 
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			
			if(emojiStr != null) {
				int start = matcher.start(3);
				
				int imgRes = EmotionUtils.getImgByName(emojiStr);
				Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgRes);
				
				if(bitmap != null) {
					int size = (int) tv.getTextSize();
					bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
					
					ImageSpan imageSpan = new ImageSpan(context, bitmap);
					spannableString.setSpan(imageSpan, start, start + emojiStr.length(), 
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
			
			
			
		}
		

		return spannableString;
	}

	static class BoreClickableSpan extends ClickableSpan {

		private Context context;
		
		public BoreClickableSpan(Context context) {
			this.context = context;
		}

		@Override
		public void onClick(View widget) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(context.getResources().getColor(R.color.txt_at_blue));
			ds.setUnderlineText(false);
		}
	}



/*
	public static SpannableString getEmotionContent( final Context context, final TextView tv, String source) {
		SpannableString spannableString = new SpannableString(source);
		Resources res = context.getResources();

		String regexEmotion = "\\[([\u4e00-\u9fa5\\w])+\\]" ;
		Pattern patternEmotion = Pattern. compile(regexEmotion);
		Matcher matcherEmotion = patternEmotion.matcher(spannableString);

		while (matcherEmotion.find()) {
			// 获取匹配到的具体字符
			String key = matcherEmotion.group();
			// 匹配字符串的开始位置
			int start = matcherEmotion.start();
			// 利用表情名字获取到对应的图片
			Integer im**s = EmotionUtils. getImgByName(key);
			if (im**s != null) {
				// 压缩表情图片
				int size = ( int) tv.getTextSize();
				Bitmap bitmap = BitmapFactory.decodeResource(res, im**s);
				Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

				ImageSpan span = new ImageSpan(context, scaleBitmap);
				spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );
			}
		}
		return spannableString;
	}
	*/


}
