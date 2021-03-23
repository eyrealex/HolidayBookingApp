from __future__ import print_function
from pip._internal.network.utils import response_chunks

import logging
import grpc
import rental_pb2_grpc
import rental_pb2



def getRentalList():
    with grpc.insecure_channel('localhost:60003') as channel:
        stub = rental_pb2_grpc.RentalServiceStub(channel)
        response = stub.rentalList(rental_pb2.RentalListRequest(value='request'))
        
        for responses in response:
            print("Rental: %s" %responses)
        
    print("python client received for get Rental List")
    
    
def getRentalBooking():
    with grpc.insecure_channel('localhost:60003') as channel:
        stub = rental_pb2_grpc.RentalServiceStub(channel)
        response = stub.rentalBooking(rental_pb2.RentalBookingRequest(car='Honda Accord'))
        response2 = stub.rentalBooking(rental_pb2.RentalBookingRequest(rentalDate='10/01/2022'))
        response3 = stub.rentalBooking(rental_pb2.RentalBookingRequest(returnDate='19/01/2022'))
        
        for responses in response:
            print()
        
        
    print("python client received for get Rental booking")
    
    
    
if __name__ == '__main__':
    logging.basicConfig()
    getRentalList()
    getRentalBooking()
    
