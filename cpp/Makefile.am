bin_PROGRAMS=snet_client_test
snet_client_test_CXXFLAGS=-g -Wall -Werror -O0 -std=c++11
snet_client_test_SOURCES=src/snet_client.cpp \
                         protobuf/snet.grpc.pb.cc \
                         protobuf/snet.pb.cc \
                         test/one_process_test.cpp
snet_client_test_LDADD=/usr/local/lib/libgrpc++_unsecure.a \
                       /usr/local/lib/libgrpc_unsecure.a \
                       /usr/local/lib/libprotobuf.a \
                       /usr/local/lib/libcares.a \
                       /usr/lib/libz.dylib

.PHONY: test
test: snet_client_test
	./$<
