from concurrent import futures
from jproperties import Properties
from zeroconf import IPVersion, ServiceInfo, Zeroconf

import logging
import rental_pb2_grpc
import rental_pb2
import grpc
import socket
import queue

class RentalService(rental_pb2_grpc.RentalServiceServicer): 
    
    rentals = ['Hertz', 'Avis Car Rental', 'Alamo', 'Budget Car Rental']
    
    def rentalList(self, request, context):
        self.rentals
        n1 = "\n"
        print(f"{n1}Getting list of rentals from server ...")
        for rental in self.rentals:
            yield rental_pb2.RentalListResponse(result=rental)
            print("Rental: %s" % rental)

    list = []
    
    def rentalBooking(self, request_iterator, context):
        self.list
        car = ""
        rentalDate = ""
        returnDate = ""
        
        
        for value in request_iterator:
            self.list.append(value)
            if (len(self.list)) == 1:
                car = value.text
            elif (len(self.list)) == 2:
                rentalDate = value.text
            elif (len(self.list)) == 3:
                returnDate = value.text
                n1 = "\n"
                print(f"Car: %s" %car)
                print(f"Rental date: %s" %rentalDate)
                print(f"Return date: %s" %returnDate)
                return rental_pb2.RentalBookingResponse(car = car, rentalDate = rentalDate, returnDate = returnDate)
        
       
        
        
        
        
def rentalServer():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    rental_pb2_grpc.add_RentalServiceServicer_to_server(RentalService(), server)
    server.add_insecure_port('[::]:60003')
    server.start()
    print('Rental Server Started')
    server.wait_for_termination()
    
def rentalRegister():
    global zeroconf
    desc = {'path': 'rental.properties'}
    
    info = ServiceInfo("_http._tcp.local.", "rental._http._tcp.local.", addresses=[socket.inet_aton("192.168.156.161")], port=60003, properties = desc, server="rental.local.",)
    zeroconf = Zeroconf()
    zeroconf.register_service(info)
    print('registering rental service ...')
    
def rentalProperties():
    configs = Properties()
    with open('rental.properties', 'rb')as config_file:
        configs.load(config_file)
    print('Service properties')
    print(configs.get("rental_service_type"))
    print(configs.get("rental_service_name"))
    print(configs.get("rental_service_description"))
    print(configs.get("rental_service_port"))

    
if __name__ == "__main__":
    logging.basicConfig()
    rentalProperties()
    rentalRegister()
    rentalServer()
        
