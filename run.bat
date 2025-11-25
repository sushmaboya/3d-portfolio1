@echo off
echo ========================================
echo Modern 3D Graphics Portfolio
echo ========================================
echo.

echo Compiling Java files...
javac --release 24 --enable-preview *.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo.
    echo Starting Modern Portfolio Application...
    echo ========================================
    echo Choose your version:
    echo 1. ModernPortfolio (Recommended - Modern UI)
    echo 2. Simple3DGraphics (Basic version)
    echo.
    set /p choice="Enter your choice (1 or 2): "
    if "%choice%"=="1" (
        java ModernPortfolio
    ) else if "%choice%"=="2" (
        java Simple3DGraphics
    ) else (
        echo Invalid choice. Starting ModernPortfolio...
        java ModernPortfolio
    )
) else (
    echo Compilation failed!
    echo Please ensure Java 8 or higher is installed.
    echo.
    echo For troubleshooting:
    echo 1. Check Java installation: java -version
    echo 2. Ensure all .java files are in the same directory
    echo 3. Try running: javac *.java
    pause
)
