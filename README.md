# SDK Unico Check
Implementação do SDK de Fotos da Unico Check na plataforma Android.

## Android
Esta biblioteca visa implementar a tecnologia unico sob a plataforma Android.

1. [**Câmera Normal**](#site)
Exibe um frame com silhueta ajustavel automaticamente com base na proporcão da tela do usuário com captura manual.

2. [**Câmera Inteligente**](#site)
Exibe um frame com silhueta ajustavel automaticamente com base na proporcão da tela do usuário, usando visão computacional na identificação da face, auxílio no enquadramento da face e captura automática.

3. [**Documentos**](#site)
Exibe um frame com silhueta ajustavel automaticamente com base na proporcão da tela do usuário com captura manual para documentos.

4. [**Customizações**](#site)
Nossa SDK conta com métodos de customização a fim de personalizar a experiência de acordo com o identidade visual de cada cliente!

## Pré-requisitos
Para a implementação adequada de nossa ferramenta, se faz necessário os seguintes requisitos:

1. **Android Studio** - IDE oficial de desenvolvimento Google. Versão 9 ou superior.

2. **minSdkVersion** - 21

3. **Maven Jitpack** - Gerenciador de bibiotecas para a IDE.

*Tenha cuidado ao implementar nossa ferramenta. Se não for implementado conforme a descrição abaixo, a ferramenta pode não funcionar corretamente.*

Siga atentamente as instruções abaixo para a implementação bem-sucedida da ferramenta.

4. **Dispositivos testados em laboratório** - Veja a lista de dispositivos testados em laboratório, [acesse aqui](https://www3.acesso.io/sdkbio/devices.html).


## Instalação
Abra o arquivo **build.gradle (Project)** e adicione o gerenciador de repositórios Jitpack manager em repositories. Seu código deve parecer com isto:

```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io'}
    }
}
```
**OBS**: Caso seu Android Studio seja a versão: *Arctic Fox* você deve alterar o arquivo **settings.gradle**. Seu código deve parecer com isto:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url 'https://jitpack.io'}
    }
}
```

Adicione suporte ao AndroidX ao seu arquivo **grandle.properties (Project)** , se faz necessário para uma melhor performance e funcionamento do frame de captura:

```
android.useAndroidX=true
android.enableJetifier=true
```

Adicione permissão de câmera ao seu arquivo **AndroidManifest.xml**:

```
<uses-permission android:name="android.permission.CAMERA" />
```

Em seguida, abra o arquivo **build.gradle (Module)** e implemente nossa dependência ao seu projeto:

```
implementation 'com.github.acesso-io:acessobio-android:+'
```

Em caso do ERRO: **Invoke-customs are only supported starting with android 0 --min-api 26** ao compilar por incompatibilidade da versão do frame min-26, adicione estas linhas em **app/build.gradle**:

```
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

## Implementação - Câmera Normal

#### **Inicializando a AcessoBio**

Importar, inicializar e receber os callbacks básicos é muito simples, siga os passos abaixo:

Vamos lá! Para importar, inicializar e obter os callbacks de retorno da AcessoBio, temos duas formas diferentes as quais descreveremos nos quadros abaixo. Para isso, abra a sua Activity/Fragment e adicione as seguintes linhas:

1. Java

```
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;

public class MainActivity extends AppCompatActivity {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, this);
    
}
```

2. Kotlin

```
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder

internal class MainActivity : AppCompatActivity() {

    private val acessoBioBuilder: IAcessoBioBuilder = AcessoBio(this, this)

}
```

Crie uma instãncia de IAcessoBioBuilder e passe dois parâmetros:
1. Context
2. Implementação da interface **AcessoBioListener**

**Exemplo 1: Implementação na classe** - Adicione a implementação da interface AcessoBioListener na classe que receberá os resultados.

1. Java

```
import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.services.dto.ErrorBio;

public class MainActivity extends AppCompatActivity implements AcessoBioListener {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, this);
    
    @Override
    public void onErrorAcessoBio(ErrorBio errorBio) { }

    @Override
    public void onUserClosedCameraManually() { }

    @Override
    public void onSystemClosedCameraTimeoutSession() { }

    @Override
    public void onSystemChangedTypeCameraTimeoutFaceInference() { }
}
```

2. Kotlin

```
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder
import com.acesso.acessobio_android.services.dto.ErrorBio


internal class MainActivity : AppCompatActivity(), AcessoBioListener {

    private val acessoBioBuilder: IAcessoBioBuilder = AcessoBio(this, this)

    override fun onErrorAcessoBio(errorBio: ErrorBio?) { }
    
    override fun onUserClosedCameraManually() { }
    
    override fun onSystemClosedCameraTimeoutSession() { }
    
    override fun onSystemChangedTypeCameraTimeoutFaceInference() { }
}
```

**Exemplo 2: Implementação no construtor** - Adicione a implementação da interface AcessoBioListener no construtor da classe **AcessoBio**.

1. Java

```
import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.services.dto.ErrorBio;

public class MainActivity extends AppCompatActivity {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, new AcessoBioListener() {
        @Override
        public void onErrorAcessoBio(ErrorBio errorBio) { }

        @Override
        public void onUserClosedCameraManually() { }

        @Override
        public void onSystemClosedCameraTimeoutSession() { }

        @Override
        public void onSystemChangedTypeCameraTimeoutFaceInference() { }
    });
}
```

2. Kotlin

```
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder
import com.acesso.acessobio_android.services.dto.ErrorBio

internal class MainActivity : AppCompatActivity() {
    
    private val acessoBioBuilder = AcessoBio(this, object: AcessoBioListener {

        override fun onErrorAcessoBio(errorBio: ErrorBio?) { }
    
        override fun onUserClosedCameraManually() { }
    
        override fun onSystemClosedCameraTimeoutSession() { }
    
        override fun onSystemChangedTypeCameraTimeoutFaceInference() { }

    })    
}
```

### Entendendo os callbacks AcessoBioListener
Existem dois métodos genéricos como **onErrorAcessoBio** e **onUserClosedCameraManually**. Veja a lista abaixo com a descrições:

1. **onErrorAcessoBio(ErrorBio errorBio)** - 
É invocado quando acontece algum erro de implementação usando nossos métodos, como, por exemplo, a passagem de um tipo de documento incorreto na captura de documentos.
2. **onUserClosedCameraManually()** - 
É invocado quando o usuário fecha a câmera manualmente, por exemplo clicando no botão de voltar.
3. **onSystemClosedCameraTimeoutSession()** - 
É invocado quando o tempo máximo de sessão é alcançado e não houve captura.
4. **onSystemChangedTypeCameraTimeoutFaceInference()** - 
É invocado quando o tempo máximo de detecção da face é alcançado e não houve inferência.

### Definindo tempo de sessão

Também é possível alterar o tempo máximo de sessão do seu usuário e o tempo máximo de captura ao utilizar a detecção da face (smart camera). Para alterar essas configurações, utilize os seguintes métodos:

1. **setTimeoutSession(double seconds)** - Configura o tempo máximo de sessão do seu usuário. Caso ele ultrapasse o tempo determinado em seu processo para capturar a foto, você poderá apresentar alguma mensagem personalizável ou instrução ao usuário. O valor padrão é de 40 segundos e seu valor mínimo também é de 40 segundos.

1. Java

```
acessoBioBuilder.setTimeoutSession(40.5);
```

2. Kotlin

```
acessoBioBuilder.setTimeoutSession(40.5)
```

No momento em que o tempo de sessão definido for expirado, o método **onSystemClosedCameraTimeoutSession**, previamente implementado através da interface **AcessoBioListener**, será invocado.

### Abrindo a câmera em modo normal

Por padrão a câmera possui o enquadramento inteligente e a captura automática habilitados. Para utilizar a câmera em modo normal, desabilite ambas as funcionalidades através dos métodos **setAutoCapture** e **setSmartFrame**.

1. Java

```
UnicoCheckCamera unicoCheckCamera = acessoBioBuilder
    .setAutoCapture(false)
    .setSmartFrame(false)
    .build();
```

2. Kotlin

```
val unicoCheckCamera: UnicoCheckCamera = acessoBioBuilder
    .setAutoCapture(false)
    .setSmartFrame(false)
    .build()
```

**OBS** - *Não é possível implementar o método setAutoCapture(true) com o método setSmartFrame(false). Ou seja, não é possível manter a captura automática sem o Smartframe, pois ele é quem realiza o enquadramento inteligênte.*

Para realizar a abertura da câmera, primeiro devemos prepará-la utilizando o método **prepareSelfieCamera**. Assim que este processo for concluido com sucesso, um objeto do tipo **UnicoCheckCameraOpener.Selfie** será retornado. Utilize este objeto para abrir a câmera através do método **open**.

1. Java

```
iAcessoBioSelfie cameraListener = new iAcessoBioSelfie() {
    @Override
    public void onSuccessSelfie(ResultCamera result) { }

    @Override
    public void onErrorSelfie(ErrorBio errorBio) { }
};

unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
    @Override
    public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
        cameraOpener.open(cameraListener);
    }

    @Override
    public void onCameraFailed(String message) {
        Log.e(TAG, message);
    }
});
```

2. Kotlin

```
val cameraListener: iAcessoBioSelfie = object : iAcessoBioSelfie {
    override fun onSuccessSelfie(result: ResultCamera?) {}

    override fun onErrorSelfie(errorBio: ErrorBio?) {}
}

unicoCheckCamera.prepareSelfieCamera(object : SelfieCameraListener {
    override fun onCameraReady(cameraOpener: UnicoCheckCameraOpener.Selfie?) {
        cameraOpener?.open(cameraListener)
    }

    override fun onCameraFailed(message: String?) {
        Log.e(TAG, message)
    }
})
```

**OBS** - *O base64 retornado possui dados com informações e que deverão ser passados inteiramente via integação com o unico check.*

Caso queira converter o base64 para Bitmap, a maneira padrão não funcionará para o Android, sendo necessário realizar o split a partir da vírgula(,) para funcionamento correto. Caso queira saber mais basta acessar este link [How to convert a Base64 string into a Bitmap image to show it in a ImageView?](https://stackoverflow.com/questions/4837110/how-to-convert-a-base64-string-into-a-bitmap-image-to-show-it-in-a-imageview)

## Implementação - Câmera Inteligente
#### **Inicializando a AcessoBio**
Importar, inicializar e receber os callbacks básicos é muito simples, siga os passos abaixo:

Vamos lá! Para importar, inicializar e obter os callbacks de retorno da AcessoBio, temos duas formas diferentes as quais descreveremos nos quadros abaixo. Para isso, abra a sua Activity/Fragment e adicione as seguintes linhas:

1. Java

```
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;

public class MainActivity extends AppCompatActivity {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, this);
    
}
```

2. Kotlin

```
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder

internal class MainActivity : AppCompatActivity() {

    private val acessoBioBuilder: IAcessoBioBuilder = AcessoBio(this, this)

}
```
Crie uma instãncia de IAcessoBioBuilder e passe dois parâmetros:

1. Context
2. Implementação da interface AcessoBioListener

**Exemplo 1: Implementação na classe** - Adicione a implementação da interface AcessoBioListener na classe que receberá os resultados.

1. Java

```
import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.services.dto.ErrorBio;

public class MainActivity extends AppCompatActivity implements AcessoBioListener {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, this);
    
    @Override
    public void onErrorAcessoBio(ErrorBio errorBio) { }

    @Override
    public void onUserClosedCameraManually() { }

    @Override
    public void onSystemClosedCameraTimeoutSession() { }

    @Override
    public void onSystemChangedTypeCameraTimeoutFaceInference() { }
}
```

2. Kotlin

```
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder
import com.acesso.acessobio_android.services.dto.ErrorBio

internal class MainActivity : AppCompatActivity(), AcessoBioListener {

    private val acessoBioBuilder: IAcessoBioBuilder = AcessoBio(this, this)

    override fun onErrorAcessoBio(errorBio: ErrorBio?) { }
    
    override fun onUserClosedCameraManually() { }
    
    override fun onSystemClosedCameraTimeoutSession() { }
    
    override fun onSystemChangedTypeCameraTimeoutFaceInference() { }
}
```

**Exemplo 2: Implementação no construtor** - Adicione a implementação da interface AcessoBioListener no construtor da classe **AcessoBio**.

1. Java

```
import com.acesso.acessobio_android.AcessoBioListener;
import com.acesso.acessobio_android.onboarding.AcessoBio;
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder;
import com.acesso.acessobio_android.services.dto.ErrorBio;

public class MainActivity extends AppCompatActivity {

    private IAcessoBioBuilder acessoBioBuilder = new AcessoBio(this, new AcessoBioListener() {
        @Override
        public void onErrorAcessoBio(ErrorBio errorBio) { }

        @Override
        public void onUserClosedCameraManually() { }

        @Override
        public void onSystemClosedCameraTimeoutSession() { }

        @Override
        public void onSystemChangedTypeCameraTimeoutFaceInference() { }
    });
}
```

2. Kotlin

```
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.IAcessoBioBuilder
import com.acesso.acessobio_android.services.dto.ErrorBio

internal class MainActivity : AppCompatActivity() {
    
    private val acessoBioBuilder = AcessoBio(this, object: AcessoBioListener {

        override fun onErrorAcessoBio(errorBio: ErrorBio?) { }
    
        override fun onUserClosedCameraManually() { }
    
        override fun onSystemClosedCameraTimeoutSession() { }
    
        override fun onSystemChangedTypeCameraTimeoutFaceInference() { }

    })    
}
```

### Entendendo os callbacks AcessoBioListener
Existem dois métodos genéricos como **onErrorAcessoBio** e **onUserClosedCameraManually**. Veja a lista abaixo com a descrições:

1. **onErrorAcessoBio(ErrorBio errorBio)** - 
É invocado quando acontece algum erro de implementação usando nossos métodos, como, por exemplo, a passagem de um tipo de documento incorreto na captura de documentos.
2. **onUserClosedCameraManually()** - 
É invocado quando o usuário fecha a câmera manualmente, por exemplo clicando no botão de voltar.
3. **onSystemClosedCameraTimeoutSession()** - 
É invocado quando o tempo máximo de sessão é alcançado e não houve captura.
4. **onSystemChangedTypeCameraTimeoutFaceInference()** - 
É invocado quando o tempo máximo de detecção da face é alcançado e não houve inferência.

### Definindo tempo de sessão
Também é possível alterar o tempo máximo de sessão do seu usuário e o tempo máximo de captura ao utilizar a detecção da face (smart camera). Para alterar essas configurações, utilize os seguintes métodos:

1. **setTimeoutSession(double seconds)** - 
Configura o tempo máximo de sessão do seu usuário. Caso ele ultrapasse o tempo determinado em seu processo para capturar a foto, você poderá apresentar alguma mensagem personalizável ou instrução ao usuário. O valor padrão é de 40 segundos e seu valor mínimo também é de 40 segundos.

2. **setTimeoutToFaceInference(double seconds)** - 
Configura o tempo máximo de captura ao utilizar a detecção da face (smart câmera). Caso o usuário encontre alguma dificuldade para capturar a foto através da detecção de face e ultrapasse o tempo determinado em seu processo, a captura será alterada automaticamente para a manual, visando facilitar a ação para o usuário. O valor padrão é de 15 segundos e seu valor mínimo é de 5 segundos.

1. Java

```
acessoBioBuilder
    .setTimeoutSession(40.5)
    .setTimeoutToFaceInference(15.0);
```

2. Kotlin

```
acessoBioBuilder
    .setTimeoutSession(40.5)
    .setTimeoutToFaceInference(15.0)
```

No momento em que o tempo de detecção da face for expirado, o método **onSystemChangedTypeCameraTimeoutFaceInference**, previamente implementado através da interface **AcessoBioListener**, será invocado e a câmera será alterada para o modo normal.
Caso a câmera seja alterada para o modo normal e o tempo de sessão definido for expirado, o método **onSystemClosedCameraTimeoutSession**, previamente implementado através da interface **AcessoBioListener**, será invocado.

### Abrindo a câmera em modo inteligente (Smart camera)
Por padrão a câmera possui o enquadramento inteligente e a captura automática habilitados, ou seja, não será necessário alterar as configurações. Caso as configurações da câmera foram alteradas previamente, é possível restaurá-las através dos métodos **setAutoCapture** e **setSmartFrame**.

1. Java

```
UnicoCheckCamera unicoCheckCamera = acessoBioBuilder
    .setAutoCapture(true)
    .setSmartFrame(true)
    .build();
```

2. Kotlin

```
val unicoCheckCamera: UnicoCheckCamera = acessoBioBuilder
    .setAutoCapture(true)
    .setSmartFrame(true)
    .build()
```

**OBS**: *Não é possível implementar o método **setAutoCapture(true)** com o método **setSmartFrame(false)**. Ou seja, não é possível manter a captura automática sem o Smartframe, pois ele é quem realiza o enquadramento inteligênte.*

Para realizar a abertura da câmera, primeiro devemos prepará-la utilizando o método **prepareSelfieCamera**. Assim que este processo for concluido com sucesso, um objeto do tipo **UnicoCheckCameraOpener.Selfie** será retornado. Utilize este objeto para abrir a câmera através do método **open**.

1. Java

```
iAcessoBioSelfie cameraListener = new iAcessoBioSelfie() {
    @Override
    public void onSuccessSelfie(ResultCamera result) { }

    @Override
    public void onErrorSelfie(ErrorBio errorBio) { }
};

unicoCheckCamera.prepareSelfieCamera(new SelfieCameraListener() {
    @Override
    public void onCameraReady(UnicoCheckCameraOpener.Selfie cameraOpener) {
        cameraOpener.open(cameraListener);
    }

    @Override
    public void onCameraFailed(String message) {
        Log.e(TAG, message);
    }
});
```

2. Kotlin

```
val cameraListener: iAcessoBioSelfie = object : iAcessoBioSelfie {
    override fun onSuccessSelfie(result: ResultCamera?) {}

    override fun onErrorSelfie(errorBio: ErrorBio?) {}
}

unicoCheckCamera.prepareSelfieCamera(object : SelfieCameraListener {
    override fun onCameraReady(cameraOpener: UnicoCheckCameraOpener.Selfie?) {
        cameraOpener?.open(cameraListener)
    }

    override fun onCameraFailed(message: String?) {
        Log.e(TAG, message)
    }
})
```

**OBS** - *O base64 retornado possui dados com informações e que deverão ser passados inteiramente via integação com o unico check.*

Caso queira converter o base64 para Bitmap, a maneira padrão não funcionará para o Android, sendo necessário realizar o split a partir da vírgula(,) para funcionamento correto. Caso queira saber mais basta acessar este link [How to convert a Base64 string into a Bitmap image to show it in a ImageView?](https://stackoverflow.com/questions/4837110/how-to-convert-a-base64-string-into-a-bitmap-image-to-show-it-in-a-imageview)

## Implementação - Documentos
#### **Inicializando a AcessoBio**
Importar, inicializar e receber os callbacks básicos é muito simples, siga os passos abaixo:

Vamos lá! Para importar, inicializar e obter os callbacks de retorno da AcessoBio, temos duas formas diferentes as quais descreveremos nos quadros abaixo. Para isso, abra a sua Activity/Fragment e adicione as seguintes linhas:

## Implementação - Customizações
#### **Inicializando a AcessoBio**
Nossa SDK conta com métodos de customização a fim de personalizar a experiência de acordo com o identidade visual de cada cliente. Segue a lista de métodos que podem ser facilmente implementadas:
