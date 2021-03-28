from __future__ import print_function
from pip._internal.network.utils import response_chunks

import logging
import grpc
import rental_pb2_grpc
import rental_pb2
import asyncio
from grpc._cython.cygrpc import asyncio


def getRentalList():
    with grpc.insecure_channel('localhost:60003') as channel:
        stub = rental_pb2_grpc.RentalServiceStub(channel)
        response = stub.rentalList(rental_pb2.RentalListRequest(value='request'))
        
        for responses in response:
            print("Rental: %s" % responses)
        
    print("python client received for get Rental List")
    
    
def getRentalBooking():
    with grpc.insecure_channel('localhost:60003') as channel:
        
        response = ['Alamo', 'Honda Accord', '10/01/2022', '19/01/2022']
        
        stub = rental_pb2_grpc.RentalServiceStub(channel)
        response1 = stub.rentalBooking(rental_pb2.RentalBookingRequest(text='Alamo'))
        response2 = stub.rentalBooking(rental_pb2.RentalBookingRequest(text='Honda Accord'))
        response3 = stub.rentalBooking(rental_pb2.RentalBookingRequest(text='10/01/2022'))
        response4 = stub.rentalBooking(rental_pb2.RentalBookingRequest(text='19/01/2022'))
        
        response.append(response1, response2, response3, response4)
        
        for responses in response:
            print(response)
        
    print("python client received for get Rental booking")
    
    
if __name__ == '__main__':
    logging.basicConfig()
    getRentalList()
    getRentalBooking()
    
