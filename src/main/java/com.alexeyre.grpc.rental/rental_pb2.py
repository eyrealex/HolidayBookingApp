# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: rental.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='rental.proto',
  package='RentalService',
  syntax='proto3',
  serialized_options=b'\n\030com.alexeyre.grpc.rentalB\021RentalServiceImplP\001\242\002\003HLW',
  create_key=_descriptor._internal_create_key,
  serialized_pb=b'\n\x0crental.proto\x12\rRentalService\"\"\n\x11RentalListRequest\x12\r\n\x05value\x18\x01 \x01(\t\"$\n\x12RentalListResponse\x12\x0e\n\x06result\x18\x01 \x01(\t\"$\n\x14RentalBookingRequest\x12\x0c\n\x04text\x18\x01 \x01(\t\"\\\n\x15RentalBookingResponse\x12\x0e\n\x06rental\x18\x01 \x01(\t\x12\x0b\n\x03\x63\x61r\x18\x02 \x01(\t\x12\x12\n\nrentalDate\x18\x03 \x01(\t\x12\x12\n\nreturnDate\x18\x04 \x01(\t2\xc6\x01\n\rRentalService\x12U\n\nrentalList\x12 .RentalService.RentalListRequest\x1a!.RentalService.RentalListResponse\"\x00\x30\x01\x12^\n\rrentalBooking\x12#.RentalService.RentalBookingRequest\x1a$.RentalService.RentalBookingResponse\"\x00(\x01\x42\x35\n\x18\x63om.alexeyre.grpc.rentalB\x11RentalServiceImplP\x01\xa2\x02\x03HLWb\x06proto3'
)




_RENTALLISTREQUEST = _descriptor.Descriptor(
  name='RentalListRequest',
  full_name='RentalService.RentalListRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='value', full_name='RentalService.RentalListRequest.value', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=31,
  serialized_end=65,
)


_RENTALLISTRESPONSE = _descriptor.Descriptor(
  name='RentalListResponse',
  full_name='RentalService.RentalListResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='result', full_name='RentalService.RentalListResponse.result', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=67,
  serialized_end=103,
)


_RENTALBOOKINGREQUEST = _descriptor.Descriptor(
  name='RentalBookingRequest',
  full_name='RentalService.RentalBookingRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='text', full_name='RentalService.RentalBookingRequest.text', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=105,
  serialized_end=141,
)


_RENTALBOOKINGRESPONSE = _descriptor.Descriptor(
  name='RentalBookingResponse',
  full_name='RentalService.RentalBookingResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  create_key=_descriptor._internal_create_key,
  fields=[
    _descriptor.FieldDescriptor(
      name='rental', full_name='RentalService.RentalBookingResponse.rental', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='car', full_name='RentalService.RentalBookingResponse.car', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='rentalDate', full_name='RentalService.RentalBookingResponse.rentalDate', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
    _descriptor.FieldDescriptor(
      name='returnDate', full_name='RentalService.RentalBookingResponse.returnDate', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR,  create_key=_descriptor._internal_create_key),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto3',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=143,
  serialized_end=235,
)

DESCRIPTOR.message_types_by_name['RentalListRequest'] = _RENTALLISTREQUEST
DESCRIPTOR.message_types_by_name['RentalListResponse'] = _RENTALLISTRESPONSE
DESCRIPTOR.message_types_by_name['RentalBookingRequest'] = _RENTALBOOKINGREQUEST
DESCRIPTOR.message_types_by_name['RentalBookingResponse'] = _RENTALBOOKINGRESPONSE
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

RentalListRequest = _reflection.GeneratedProtocolMessageType('RentalListRequest', (_message.Message,), {
  'DESCRIPTOR' : _RENTALLISTREQUEST,
  '__module__' : 'rental_pb2'
  # @@protoc_insertion_point(class_scope:RentalService.RentalListRequest)
  })
_sym_db.RegisterMessage(RentalListRequest)

RentalListResponse = _reflection.GeneratedProtocolMessageType('RentalListResponse', (_message.Message,), {
  'DESCRIPTOR' : _RENTALLISTRESPONSE,
  '__module__' : 'rental_pb2'
  # @@protoc_insertion_point(class_scope:RentalService.RentalListResponse)
  })
_sym_db.RegisterMessage(RentalListResponse)

RentalBookingRequest = _reflection.GeneratedProtocolMessageType('RentalBookingRequest', (_message.Message,), {
  'DESCRIPTOR' : _RENTALBOOKINGREQUEST,
  '__module__' : 'rental_pb2'
  # @@protoc_insertion_point(class_scope:RentalService.RentalBookingRequest)
  })
_sym_db.RegisterMessage(RentalBookingRequest)

RentalBookingResponse = _reflection.GeneratedProtocolMessageType('RentalBookingResponse', (_message.Message,), {
  'DESCRIPTOR' : _RENTALBOOKINGRESPONSE,
  '__module__' : 'rental_pb2'
  # @@protoc_insertion_point(class_scope:RentalService.RentalBookingResponse)
  })
_sym_db.RegisterMessage(RentalBookingResponse)


DESCRIPTOR._options = None

_RENTALSERVICE = _descriptor.ServiceDescriptor(
  name='RentalService',
  full_name='RentalService.RentalService',
  file=DESCRIPTOR,
  index=0,
  serialized_options=None,
  create_key=_descriptor._internal_create_key,
  serialized_start=238,
  serialized_end=436,
  methods=[
  _descriptor.MethodDescriptor(
    name='rentalList',
    full_name='RentalService.RentalService.rentalList',
    index=0,
    containing_service=None,
    input_type=_RENTALLISTREQUEST,
    output_type=_RENTALLISTRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
  _descriptor.MethodDescriptor(
    name='rentalBooking',
    full_name='RentalService.RentalService.rentalBooking',
    index=1,
    containing_service=None,
    input_type=_RENTALBOOKINGREQUEST,
    output_type=_RENTALBOOKINGRESPONSE,
    serialized_options=None,
    create_key=_descriptor._internal_create_key,
  ),
])
_sym_db.RegisterServiceDescriptor(_RENTALSERVICE)

DESCRIPTOR.services_by_name['RentalService'] = _RENTALSERVICE

# @@protoc_insertion_point(module_scope)
