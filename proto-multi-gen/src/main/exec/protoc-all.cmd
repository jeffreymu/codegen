@echo off
set WORK_DIR=%~dp0
set N_WORK_DIR=%WORK_DIR:~0,-6%
set ENV_PATH=%PATH%
@echo %ENV_PATH%

set EXEC_PATH=%N_WORK_DIR%exec
set ENV_PATH=%PATH%;%EXEC_PATH%
@echo %ENV_PATH%

set AUTO_PHASE_ONE="autogen_all_1.protobin"
%EXEC_PATH%/protoc.exe --version
%EXEC_PATH%/protoc.exe -I. --include_imports --descriptor_set_out=%AUTO_PHASE_ONE% *.proto
