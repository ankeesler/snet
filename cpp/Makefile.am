FLAGS=-g -Wall -Werror -O0 -std=c++11
LIBS=/usr/local/lib/libgrpc++_unsecure.a \
     /usr/local/lib/libgrpc_unsecure.a \
     /usr/local/lib/libprotobuf.a \
     /usr/local/lib/libcares.a \
     /usr/lib/libz.dylib \
     libsnetclient.a
UNITY_SOURCE=../../Unity/src/unity.c

noinst_LIBRARIES=libsnetclient.a

libsnetclient_a_CXXFLAGS=$(FLAGS)
libsnetclient_a_SOURCES=src/snet_client.cpp \
                        src/snet_node.cpp \
                        src/snet_admin.cpp \
                        protobuf/snet.grpc.pb.cc \
                        protobuf/snet.pb.cc

bin_PROGRAMS=one_process_test multi_process_test echo_app cli_app sensor_app sink_app

one_process_test_CXXFLAGS=$(FLAGS)
one_process_test_SOURCES=$(UNITY_SOURCE) test/one_process_test.cpp
one_process_test_LDADD=$(LIBS)

multi_process_test_CXXFLAGS=$(FLAGS)
multi_process_test_SOURCES=$(UNITY_SOURCE) test/multi_process_test.cpp
multi_process_test_LDADD=$(LIBS)

echo_app_CXXFLAGS=$(FLAGS)
echo_app_SOURCES=app/echo/echo_app.cpp
echo_app_LDADD=$(LIBS)

cli_app_CXXFLAGS=$(FLAGS)
cli_app_SOURCES=app/cli/cli_app.cpp
cli_app_LDADD=$(LIBS)

sensor_app_CXXFLAGS=$(FLAGS)
sensor_app_SOURCES=app/sensor_sink/sensor_app.cpp
sensor_app_LDADD=$(LIBS)

sink_app_CXXFLAGS=$(FLAGS)
sink_app_SOURCES=app/sensor_sink/sink_app.cpp
sink_app_LDADD=$(LIBS)

.PHONY: apps
apps: echo_app cli_app sensor_app sink_app

.PHONY: test
test: one_process_test multi_process_test apps
	@echo "running one_process_test..."
	@./one_process_test
	@echo "running multi_process_test..."
	@./multi_process_test
