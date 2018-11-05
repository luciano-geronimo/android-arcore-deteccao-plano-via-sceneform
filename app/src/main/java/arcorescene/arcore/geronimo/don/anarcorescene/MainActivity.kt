package arcorescene.arcore.geronimo.don.anarcorescene

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val MIN_OPENGL_VERSION = 3.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //testa suporte
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }
        //seta o layout. No layout tem um ArFragment
        setContentView(R.layout.activity_main)
        
    }
    /**
     * Testa se o dispositivo é capaz de rodar o arcore. Retorna true se for, false de falhar por falta de capacidades
     * de opengl ou de versão do android.
     * */
    private fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            Log.e("myApp", "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        val openGLVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).deviceConfigurationInfo.glEsVersion
        if (openGLVersionString.toDouble() < MIN_OPENGL_VERSION){
            Log.e("myApp", "Sceneform requires OpenGL ES 3.0 or later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        return true;
    }
}
