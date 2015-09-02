@echo off
path=c:/program files/java/jdk1.6.0_23/bin;%path%
javac -d . *.java
pause
echo Main-Class: data.Arjong>MANIFEST.MF
jar cvfm resource.pk MANIFEST.MF data org Resource dbformat
DEL MANIFEST.MF
cls
echo Main-Class: config.Config>MANIFEST.MF
jar cvfm Config.jar MANIFEST.MF config
DEL MANIFEST.MF
cls
echo Main-Class: client.Client>MANIFEST.MF
echo SplashScreen-Image: splash/splash.png>>MANIFEST.MF
echo Class-path: resource.pk>>MANIFEST.MF
jar cvfm Arjong.jar MANIFEST.MF client org splash
DEL MANIFEST.MF

pause