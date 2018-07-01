package com.appaloossa.honorgame.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appaloossa.honorgame.R;

import butterknife.BindString;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created on 9/22/17.
 */

public class HonorBaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;

    @BindString(R.string.app_name) String mAppNameString;
    @BindString(R.string.generic_dialog_message_loading) String mGenericDialogMessageString;
    @BindString(R.string.generic_dialog_button_ok) String mGenericDialogButtonOkString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void showProgressDialog() {
        showProgressDialog(mAppNameString, mGenericDialogMessageString, false);
    }

    protected void showProgressDialog(String dialogTitle, String dialogMessage, boolean isCancelable) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(dialogMessage); // Setting Message
        mProgressDialog.setTitle(dialogTitle); // Setting Title
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        mProgressDialog.show(); // Display Progress Dialog
        mProgressDialog.setCancelable(isCancelable);
    }

    protected void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    protected AlertDialog showErrorMessage(String dialogMessage) {
        return showErrorMessage(dialogMessage, null);
    }

    protected AlertDialog showErrorMessage(String dialogMessage, DialogInterface.OnClickListener positiveClickListener) {
        if (positiveClickListener == null) {
            positiveClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        return showAlertDialog(mAppNameString, dialogMessage, mGenericDialogButtonOkString, positiveClickListener, null, null);
    }

    protected AlertDialog showAlertDialog(String dialogTitle, String dialogMessage, String positiveButtonString, DialogInterface.OnClickListener positiveClickListener, String negativeButtonString, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogMessage).setTitle(dialogTitle);
        if (positiveClickListener != null) builder.setPositiveButton(positiveButtonString, positiveClickListener);
        if (negativeClickListener != null) builder.setNegativeButton(negativeButtonString, negativeClickListener);
        mAlertDialog = builder.create();
        return mAlertDialog;
    }

}
