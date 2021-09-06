package com.example.sdkunicocheck.useful;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.iAcessoBioDocument;
import com.acesso.acessobio_android.iAcessoBioSelfie;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.onboarding.IAcessoBioTheme;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCamera;
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener;
import com.acesso.acessobio_android.onboarding.camera.document.DocumentCameraListener;
import com.acesso.acessobio_android.onboarding.camera.selfie.SelfieCameraListener;
import com.acesso.acessobio_android.onboarding.types.DocumentType;
import com.acesso.acessobio_android.services.dto.ErrorBio;
import com.acesso.acessobio_android.services.dto.ResultCamera;
import com.example.sdkunicocheck.R;

public class MyAcessoBio {

    IAcessoBioBuilder acessoBioBuilder;
    UnicoCheckCamera unicoCheckCamera;
    iAcessoBioDocument bioDocument;
    iAcessoBioSelfie bioSelfie;
    Context context;
    IAcessoBioTheme unicoTheme  = new IAcessoBioTheme() {
        @Override
        public Object getColorBackground() {
            return R.color.colorAccent;
        }

        @Override
        public Object getColorBoxMessage() {
            return R.color.colorBlack;
        }

        @Override
        public Object getColorTextMessage() {
            return R.color.colorGray;
        }

        @Override
        public Object getColorBackgroundPopupError() {
            return R.color.colorGreen;
        }

        @Override
        public Object getColorTextPopupError() {
            return R.color.colorGreenMaskBorder;
        }

        @Override
        public Object getColorBackgroundButtonPopupError() {
            return R.color.colorGrey;
        }

        @Override
        public Object getColorTextButtonPopupError() {
            return R.color.colorIntroDescription;
        }

        @Override
        public Object getColorBackgroundTakePictureButton() {
            return R.color.colorOrange;
        }

        @Override
        public Object getColorIconTakePictureButton() {
            return R.color.colorPrimary;
        }

        @Override
        public Object getColorBackgroundBottomDocument() {
            return R.color.colorPrimaryDark;
        }

        @Override
        public Object getColorTextBottomDocument() {
            return R.color.colorWhite;
        }

        @Override
        public Object getColorSilhouetteSuccess() {
            return R.color.darkGrey;
        }

        @Override
        public Object getColorSilhouetteError() {
            return R.color.success_stroke_color;
        }
    };;

    public MyAcessoBio(Context context) {

        this.context = context;

        acessoBioBuilder = new AcessoBio(context, new AcessoBioListener() {
            @Override
            public void onErrorAcessoBio(ErrorBio errorBio) {
                Toast.makeText(context, errorBio.getDescription(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onUserClosedCameraManually() {
                Toast.makeText(context, "onUserClosedCameraManually", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSystemClosedCameraTimeoutSession() {
                Toast.makeText(context, "onSystemClosedCameraTimeoutSession", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSystemChangedTypeCameraTimeoutFaceInference() {
                Toast.makeText(context, "onSystemChangedTypeCameraTimeoutFaceInference", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void setBase64InImageView(ImageView imageView, String base64){
        Bitmap image = ImageUseful.convert(base64);
        imageView.setImageBitmap(image);
    }

    public void takePitureDocument(DocumentType documentType, ImageView imageView){
        unicoCheckCamera = acessoBioBuilder.build();

        bioDocument = new iAcessoBioDocument(){
            @Override
            public void onSuccessDocument(String base64) {
                setBase64InImageView(imageView, base64);
            }

            @Override
            public void onErrorDocument(String error) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show();
            }
        };

        unicoCheckCamera.prepareDocumentCamera(new DocumentCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Document cameraOpener) {
                cameraOpener.open(documentType, bioDocument);
            }

            @Override
            public void onCameraFailed(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void takePitureNormalCamera(ImageView imageView){
        unicoCheckCamera = acessoBioBuilder
                .setAutoCapture(false)
                .setSmartFrame(false)
                .build();

        bioSelfie = new iAcessoBioSelfie() {
            @Override
            public void onSuccessSelfie(ResultCamera result) {
                setBase64InImageView(imageView, result.getBase64());
            }

            @Override
            public void onErrorSelfie(ErrorBio errorBio) {
                Toast.makeText(context, errorBio.getDescription(), Toast.LENGTH_LONG).show();
            }
        };

        unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                cameraOpener.open(bioSelfie);
            }

            @Override
            public void onCameraFailed(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void takePitureSmartCamera(ImageView imageView){
        unicoCheckCamera = acessoBioBuilder
                .setAutoCapture(true)
                .setSmartFrame(true)
                .build();

        bioSelfie = new iAcessoBioSelfie() {
            @Override
            public void onSuccessSelfie(ResultCamera result) {
                setBase64InImageView(imageView, result.getBase64());
            }

            @Override
            public void onErrorSelfie(ErrorBio errorBio) {
                Toast.makeText(context, errorBio.getDescription(), Toast.LENGTH_LONG).show();
            }
        };

        unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                cameraOpener.open(bioSelfie);
            }

            @Override
            public void onCameraFailed(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void takePitureCustomCamera(ImageView imageView){
        acessoBioBuilder.setTheme(unicoTheme);
        unicoCheckCamera = acessoBioBuilder
                .setAutoCapture(true)
                .setSmartFrame(true)
                .build();

        bioSelfie = new iAcessoBioSelfie() {
            @Override
            public void onSuccessSelfie(ResultCamera result) {
                setBase64InImageView(imageView, result.getBase64());
            }

            @Override
            public void onErrorSelfie(ErrorBio errorBio) {
                Toast.makeText(context, errorBio.getDescription(), Toast.LENGTH_LONG).show();
            }
        };

        unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
            @Override
            public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
                cameraOpener.open(bioSelfie);
            }

            @Override
            public void onCameraFailed(String message) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
