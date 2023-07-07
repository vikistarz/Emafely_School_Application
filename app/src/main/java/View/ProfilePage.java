package View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.emafelyapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Utility.AppConstant;

public class ProfilePage extends AppCompatActivity {

    private View backArrow;

    private LinearLayout addPhotoLayout;
    private Button accountInformationButton, languagesButton, notificationButton, contactButton, privacySecurityButton, logoutButton;

    private TextView editInfo;
    private ImageView imagePicture;
    private boolean isPhotoSelected;
    private String currentPhotoPath;

    public static final int CAMERA_REQUEST_CODE = 3;
    public static final int GALLERY_REQUEST_CODE= 6;
    public static final int ASK_MULTIPLE_PERMISSION_REQUEST = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        inItView();
        inItListener();
    }


    public void inItView() {
        editInfo = findViewById(R.id.tv_edit);
        addPhotoLayout = findViewById(R.id.layout_add_photo);
        imagePicture = findViewById(R.id.img_picture);
         accountInformationButton = findViewById(R.id.btn_account_info);
        languagesButton = findViewById(R.id.btn_languages);
        notificationButton = findViewById(R.id.btn_notification);
        contactButton = findViewById(R.id.btn_contact);
        privacySecurityButton = findViewById(R.id.btn_privacy_security);
        logoutButton = findViewById(R.id.btn_log_out);
        backArrow = findViewById(R.id.arrow_back);
    }

    public void inItListener() {

       accountInformationButton();
       backArrow();
       addPhotoLayout();
       logoutButton();
       editInfo();
    }

    private void editInfo() {
        Intent myIntent = new Intent(ProfilePage.this, AccountInformation.class);
        startActivity(myIntent);
    }

    private void addPhotoLayout() {
        addPhotoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ProfilePage.this);
                // inflate view

                View myView = getLayoutInflater().inflate(R.layout.profile_picture_dialig, null);
                myBuilder.setView(myView);

                TextView takePhoto = myView.findViewById(R.id.tv_take_photo);
                TextView selectGallery = myView.findViewById(R.id.tv_select_gallery);
                Button cancelButton = myView.findViewById(R.id.btn_cancel);

              takePhoto.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      isPhotoSelected = true;
                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                          checkIfPermissionHasBeenGranted();
                      }

                  }
              });

              selectGallery.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      isPhotoSelected = false;
                      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                          checkIfPermissionHasBeenGranted();
                      }

                  }
              });


              cancelButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      finish();

                  }
              });

        AlertDialog myDialog = myBuilder.create();
        myDialog.show();

                Window myWindow = myDialog.getWindow();
                myWindow.setGravity(Gravity.BOTTOM);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkIfPermissionHasBeenGranted() {
        //ask the user to grant you the permission to perform the operation
        if (ContextCompat.checkSelfPermission(ProfilePage.this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(ProfilePage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(ProfilePage.this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {

            // you can use the Api that requires the permission
            if (isPhotoSelected) {
                selectCameraPicture();
            } else {
                selectGalleryPicture();
            }

//           Toast.makeText(this, "He has granted all permission", Toast.LENGTH_SHORT).show();
        } else {
//             you can directly ask for the permission.
//           the registered activity result Callback gets the result of these request.
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, ASK_MULTIPLE_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ASK_MULTIPLE_PERMISSION_REQUEST) {
            // if request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission is granted. continue the action or workflow in your app.
//                Toast.makeText(this, "Permission granted 1", Toast.LENGTH_SHORT).show();

                if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow in your app'
//                    Toast.makeText(this, "Permission granted 2", Toast.LENGTH_SHORT).show();

                    if (isPhotoSelected) {
                        selectCameraPicture();
                    } else {
                        selectGalleryPicture();
                    }
                }
            } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void selectCameraPicture(){
        Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //ensure that there is a camera activity to handle the intent
        if(myIntent.resolveActivity(getPackageManager())!= null){
//            create the file where the photo should go
            File photoFile = null;
            try{
                photoFile = createImageFile();
            }
            catch(IOException ex){
                //error occur while creating the file
                return;
            }
//            continue only if the file was successfully created
            if(photoFile != null){
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.emafelyapp.fileProvider", photoFile);
                myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(myIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    private void selectGalleryPicture(){
        Intent myIntent = new Intent();
        myIntent.setType("image/*");
        myIntent.setAction(Intent.ACTION_GET_CONTENT);

//        pass the constant to compare it with the returned requestCode
        startActivityForResult(Intent.createChooser(myIntent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    private File createImageFile() throws IOException {
//        create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhMMss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName /*prefix*/, ".jpg", /*suffix*/ storageDir /*directory*/);

//        save a file:path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        System.out.println("path is" + currentPhotoPath);
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            File myFile = new File(currentPhotoPath);
            Uri myUri = FileProvider.getUriForFile(this,"com.example.emafelyapp.fileProvider", myFile);
            galleryAddPic();
            imagePicture.setImageURI(myUri);
        }
        else if(requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri selectedImageUri = data.getData();
            if(null != selectedImageUri){
//                update the preview image in the layout
                imagePicture.setImageURI(selectedImageUri);
            }
        }
    }

    private void galleryAddPic(){
        Intent myIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File myFile = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(myFile);
        myIntent.setData(contentUri);
        this.sendBroadcast(myIntent);
    }

    private void logoutButton() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, MainPage1.class);
                startActivity(myIntent);
            }
        });
    }

    private void accountInformationButton() {
        accountInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, AccountInformation.class);
                startActivity(myIntent);
            }
        });
    }

    private void backArrow() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfilePage.this, StudentDashboard.class);
                startActivity(myIntent);
            }
        });
    }

}