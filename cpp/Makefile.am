bin_PROGRAMS=one_process_test multi_process_test echo_app

FLAGS=-g -Wall -Werror -O0 -std=c++11
LIBS=/usr/local/lib/libgrpc++_unsecure.a \
     /usr/local/lib/libgrpc_unsecure.a \
     /usr/local/lib/libprotobuf.a \
     /usr/local/lib/libcares.a \
     /usr/lib/libz.dylib
TEST_FILES=../../Unity/src/unity.c

one_process_test_CXXFLAGS=$(FLAGS)
one_process_test_SOURCES=src/snet_client.cpp \
                         protobuf/snet.grpc.pb.cc \
                         protobuf/snet.pb.cc \
                         $(TEST_FILES) \
                         test/one_process_test.cpp
one_process_test_LDADD=$(LIBS)

multi_process_test_CXXFLAGS=$(FLAGS)
multi_process_test_SOURCES=src/snet_client.cpp \
                           protobuf/snet.grpc.pb.cc \
                           protobuf/snet.pb.cc \
                          $(TEST_FILES) \
                           test/multi_process_test.cpp
multi_process_test_LDADD=$(LIBS)

echo_app_CXXFLAGS=$(FLAGS)
echo_app_SOURCES=src/snet_client.cpp \
                 protobuf/snet.grpc.pb.cc \
                 protobuf/snet.pb.cc \
                 app/echo_app.cpp
echo_app_LDADD=$(LIBS)

.PHONY: test
test: one_process_test multi_process_test echo_app
	./one_process_test
	./multi_process_test
