package com.example.facerecognition.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facerecognition.R;
import com.example.facerecognition.bean.AttributesBean;
import com.example.facerecognition.bean.FaceResult;
import com.example.facerecognition.bean.FacesBean;
import com.example.facerecognition.listener.OnGetFaceListener;
import com.example.facerecognition.persent.FacePersent;
import com.example.facerecognition.util.BASE64Encoder;
import com.example.facerecognition.util.ImageToBase64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class MainActivity extends BaseActivity {
    public static final int CAMERA = 1025;// 拍照的请求码
    public static final int ALBUM = 1026;// 选择图片的请求码
    private RelativeLayout imageView_parent;
    private ImageView imageView;
    private RadioGroup titleGroup;
    private String photoCameraPath;
    private String photoLocalPath;
    private ImageToBase64 imageToBase64;
    private Bitmap bitmap;
    private TextView text_to_get_picture;

    private Uri uri;
    Intent intent = new Intent("com.android.camera.action.CROP");



    private static final String TAG = "hdu";

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int CHOOSE_PHOTO = 3;

    private Button start;

    private static final int REQUEST_TAKE_PHOTO_CODE = 123;

    private Button chooseFromAlbum;
    private FacePersent facePersent;
    private TextView age;
    private TextView gender;
    private static final int REQUEST_PHOTO_CODE = 200;



    @Override
    protected void loadData() {

    }

    @Override
    protected void setListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.image:

                        new AlertDialog.Builder(MainActivity.this)

                                .setPositiveButton("相机", new DialogInterface.OnClickListener() {

                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {

                                        DongTaiShare();

                                        //从相机获取图片

                                        getPicFromCamera();

                                    }

                                }).setNegativeButton("相册", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {

                                //从相册获取图片

                                getPicFromAlbm();
                            }

                        }).create().show();

                        break;

                }

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String api_key = "wzkJVoJSHcqJjSI-iRteF4SxdVWld8BU";
                String api_secret = "IUy-2tHZCNJpb4ZGzdj-rSPieHvb5--4";
                String image_base64="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";
                String return_attributes="gender,age";
                facePersent=new FacePersent();
                facePersent.post("https://api-cn.faceplusplus.com/facepp/v3/detect", api_key, api_secret, image_base64, return_attributes, new OnGetFaceListener() {
                    @Override
                    public void getSuccess(final FaceResult faceResult) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                    //存入SP
                                    FacesBean facesBeans=faceResult.getFaces().get(0);
                                    AttributesBean attributes =facesBeans.getAttributes();
                                    AttributesBean.AgeBean ageBean=attributes.getAge();
                                    String textage=String.valueOf(ageBean.getValue());
                                AttributesBean.GenderBean genderBean=attributes.getGender();
                                String textgender=genderBean.getValue();
                                age.setText("年龄:"+textage);
                                gender.setText("性别:"+textgender);

                                    finish();

                            }
                        });
                    }

                    @Override
                    public void getFailed(Throwable throwable) {

                    }
                });

            }
        });


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        titleGroup=findViewById(R.id.titleGroup);
        imageView=findViewById(R.id.image);
        imageView_parent=findViewById(R.id.image_parent);
        start=findViewById(R.id.radio3);
        text_to_get_picture=findViewById(R.id.text_to_get_picture);
        //chooseFromAlbum=findViewById(R.id.radio2);



    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void DongTaiShare() {

        if (Build.VERSION.SDK_INT >= 23) {

            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.CAMERA};

            ActivityCompat.requestPermissions(this, mPermissionList, 123);

        }

    }



    //调用系统相机

    private void getPicFromCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, 1);

    }



    //调用相册

    private void getPicFromAlbm() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        photoPickerIntent.setType("image/*");

        startActivityForResult(photoPickerIntent, 2);



    }

    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {

            // 调用相机后返回

            case 1:

                if (resultCode == RESULT_OK) {
                    uri = intent.getData();

                    final Bitmap photo = intent.getParcelableExtra("data");

                    //给头像设置你相机拍的照片

                    imageView.setImageBitmap(photo);
                    text_to_get_picture.setText(null);


                }

                break;

            //调用相册后返回

            case 2:

                if (resultCode == RESULT_OK) {

                    uri = intent.getData();
//                    final Bitmap photo = intent.getParcelableExtra("data");
//                    imageView.setImageBitmap(photo);
//                    uri = intent.getData();
                    Bitmap bitmap = null;
                    ContentResolver resolver = getContentResolver();
                    try {
                        bitmap = BitmapFactory.decodeStream(resolver.openInputStream(uri));
                        Bitmap bitmap1=rotateBitmapByDegree(bitmap,90);
                        imageView.setImageBitmap(bitmap1);
                        text_to_get_picture.setText(null);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

                break;

            //调用剪裁后返回

//            case 3:
//
//                Bundle bundle = intent.getExtras();
//
//                if (bundle != null) {
//
//                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
//
//                    Bitmap image = bundle.getParcelable("data");
//
//                    //设置到ImageView上
//
//                    imageView.setImageBitmap(image);
//
//                    //也可以进行一些保存、压缩等操作后上传
//
//                    String path = saveImage("userHeader", image);
//
//                    File file = new File(path);
//
//            /*
//
//             *这里可以做上传文件的额操作
//
//             */
//
//        }

//        break;

    }

    }



    /**

     * 裁剪图片

     */

    private void cropPhoto(Uri uri) {


        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);

        intent.putExtra("outputY", 300);

        intent.putExtra("return-data", true);

        startActivityForResult(intent, 3);

    }

    public String saveImage(String name, Bitmap bmp) {

        File appDir = new File(Environment.getExternalStorageDirectory().getPath());

        if (!appDir.exists()) {

            appDir.mkdir();

        }

        String fileName = name + ".jpg";

        File file = new File(appDir, fileName);

        try {

            FileOutputStream fos = new FileOutputStream(file);

            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            fos.flush();

            fos.close();

            return file.getAbsolutePath();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String imageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
      //  System.out.println("本地图片转换Base64:" + encoder.encode(Objects.requireNonNull(data)));
        return encoder.encode(Objects.requireNonNull(data));
    }

    public String getImgFromCamra(Context context) {
        String state = Environment.getExternalStorageState();
        File mFolder;
        String mImgName;
        // 先检测是不是有内存卡。
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            mFolder = new File(Environment.getExternalStorageDirectory(),
                    "bCache");
            // 判断手机中有没有这个文件夹，没有就新建。
            if (!mFolder.exists()) {
                mFolder.mkdirs();
            }
            // 自定义图片名字，这里是以毫秒数作为图片名。
            mImgName = System.currentTimeMillis() + ".jpg";
            Uri uri = Uri.fromFile(new File(mFolder, mImgName));
            // 调用系统拍照功能。打开箱机
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            MainActivity.this.startActivityForResult(intent, CAMERA);
            return mFolder + File.separator + mImgName;
        } else {
            Toast.makeText(context, "未检测到SD卡", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void getImgFromAlbum(Context context) {
        // 调用本地图库。
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ALBUM);
    }
}
