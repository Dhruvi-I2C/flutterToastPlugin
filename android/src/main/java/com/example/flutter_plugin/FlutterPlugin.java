package com.example.flutter_plugin;



import android.app.Activity;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Objects;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterToastPlugin */
public class FlutterPlugin implements MethodCallHandler {

  private  final MethodChannel channel;
  private final Activity activity;

  /** Plugin registration. */


  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_plugin");
    channel.setMethodCallHandler(new FlutterPlugin(registrar.activity(),channel));
  }

  private FlutterPlugin(Activity activity, MethodChannel channel){
    this.activity = activity;
    this.channel = channel;
    this.channel.setMethodCallHandler(this);
  }


  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override
  public void onMethodCall(MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
    else if(call.method.equals("showToast")){
      String msg = Objects.requireNonNull(call.argument("msg")).toString();
      Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
    }

    else {
      result.notImplemented();
    }
  }
}