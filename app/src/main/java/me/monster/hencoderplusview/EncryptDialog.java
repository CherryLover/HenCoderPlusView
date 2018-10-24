package me.monster.hencoderplusview;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.Cipher;

/**
 * @description
 * @author: jiangjiwei
 * Created in  2018/10/14 08:29
 */
public class EncryptDialog extends DialogFragment implements View.OnClickListener {

    public static EncryptDialog newInstance() {
        return new EncryptDialog();
    }

    private static final String TAG = "EncryptDialog";

    private LoginActivity mLoginActivity;
    private TextView tvErrorMsg;
    private TextView btnCancel;

    private FingerprintManager mFingerprintManager;
    private CancellationSignal mCancellationSignal;
    private Cipher mCipher;

    private boolean isCancelSelf;

    private int errorTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFingerprintManager = (FingerprintManager) getContext().getSystemService(Context.FINGERPRINT_SERVICE);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginActivity = (LoginActivity) getContext();
    }

    public void setCipher(Cipher cipher) {
        mCipher = cipher;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fingerprint, container, false);
        tvErrorMsg = root.findViewById(R.id.tv_error_msg);
        btnCancel = root.findViewById(R.id.tv_try_cancel);
        btnCancel.setOnClickListener(this);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        startEncryptListener(mCipher);
    }

    private void startEncryptListener(Cipher cipher) {
        isCancelSelf = false;
        mFingerprintManager.authenticate(new FingerprintManager.CryptoObject(cipher), mCancellationSignal, 0, new
                FingerprintManager.AuthenticationCallback() {

                    @Override
                    public void onAuthenticationError(int errorCode, CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        if (!isCancelSelf) {
                            tvErrorMsg.setText(errString);
                            if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
                                Toast.makeText(mLoginActivity, errString, Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        }
//                        if (errorCode == 7) {
//                            Toast.makeText(mLoginActivity,"尝试次数过多，请稍后重试",Toast.LENGTH_SHORT).show();
//                            mLoginActivity.finish();
//                            return;
//                        }
                        tvErrorMsg.setText("指纹认证失败");
                        Log.e(TAG, "onAuthenticationError: + error Code : " + errorCode + " errorString : " +
                                errString);
                        Toast.makeText(mLoginActivity, "指纹认证失败：" + errorCode + errString.toString(), Toast
                                .LENGTH_SHORT);
                    }

                    @Override
                    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                        super.onAuthenticationHelp(helpCode, helpString);
                        tvErrorMsg.setText(helpString);
                        Log.e(TAG, "onAuthenticationError: + help Code : " + helpCode + " helpString : " + helpString);
                        Toast.makeText(mLoginActivity, "指纹认证帮助：" + helpCode + helpString.toString(), Toast
                                .LENGTH_SHORT);
                    }

                    @Override
                    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(mLoginActivity, "指纹认证成功：", Toast.LENGTH_SHORT);
                        MainActivity.start(mLoginActivity);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        errorTime += 1;
                        tvErrorMsg.setText("指纹验证失败" + errorTime + " 次");
//                tvErrorMsg.setText();
//                mLoginActivity.finish();
                    }
                }, null);
    }

    @Override
    public void onClick(View view) {
        EncryptDialog.this.dismiss();
        stopEncryptListener();
    }

    private void stopEncryptListener() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
            isCancelSelf = true;
        }
    }
}
