@echo off
title Zipkin Server

echo ============================================
echo   Zipkin Server - Tracing Service
echo   Start URL: http://localhost:9411
echo ============================================
echo.

set JDK17=F:\java\jdk-17.0.6\bin\java.exe

if not exist "%JDK17%" (
    echo [ERROR] JDK 17 not found: %JDK17%
    pause
    exit /b 1
)

echo [INFO] Using JDK 17: %JDK17%
echo [INFO] Starting Zipkin Server...
echo [INFO] URL: http://localhost:9411
echo [INFO] Press Ctrl+C to stop
echo.

"%JDK17%" -jar "%~dp0zipkin.jar"

pause
