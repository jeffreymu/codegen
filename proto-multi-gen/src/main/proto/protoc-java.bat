@echo off
set WORK_DIR=%~dp0
set N_WORK_DIR=%WORK_DIR:~0,-6%
set ENV_PATH=%PATH%
@echo %ENV_PATH%

set EXEC_PATH=%N_WORK_DIR%proto-tool
set ENV_PATH=%PATH%;%EXEC_PATH%
@echo %ENV_PATH%

set AUTO_PHASE_ONE="autogen_java_all.protobin"
%EXEC_PATH%/protoc.exe --version
%EXEC_PATH%/protoc.exe --java_out=. -I. --include_imports --descriptor_set_out=%AUTO_PHASE_ONE% *.proto
exit;