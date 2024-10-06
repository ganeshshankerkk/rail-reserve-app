# Introducing Rail Reserve

A Train Ticket Booking API implemented using Spring Boot. It allows users to book, update, and cancel tickets, and retrieve information about trains and seats.

## Getting Started
- Download the zip or clone the Git repository
- Unzip the zip file (if you downloaded one)
- Open Spring Tool Suite / Eclipse
- File -> Open projects from file system... -> Navigate to the folder where you unzipped the zip
- Select the right project
- Choose the Spring
-  Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application (Eclipse) / Spring Boot App (Spring Tool Suite)
  
## API Documentation

Below is a list of API endpoints with their respective input and output. Please note that the application needs to be
running for the following endpoints to work.

### Book a ticket

> Books a ticket for a passenger based on the provided ticket request details. Ticket is booked based on multiple validations including train and seat availability, and by making sure that the passenger has no seats allocated already at the time of booking.

Endpoint

```http
POST /api/ticket/book
```

Example of body

```json
{
    "from": "bangalore",
    "to": "coimbatore",
    "price-paid":30,
    "passenger": {
        "email": "sam.smith@railreserve.com",
        "first-name": "Sam",
        "last-name": "Smith"
    }
}
```

Parameters


| Parameter      | Type | Description                                           |
| -------------- |----------| ----------------------------------------------------- |
| `from` |`String` |  the starting railway station             |
| `to`         | `String` | The destination railway station |
| `price-paid`      |`BigDecimal` |  The price paid for the ticket  |
| `email`      |`String` |  The email of the passenger |
| `first-name`      |`String` |  First name of the passenger  |
| `last-name`      |`String` |  Last name of the passenger  |

Example output

```json
{
    "success": true,
    "data": {
        "email": "sam.smith@railreserve.com",
        "ticket": {
            "id": "17ac03d3-8eaa-4434-bd91-d585f2b6404a",
            "from": "bangalore",
            "to": "coimbatore",
            "cost": 30,
            "seat": {
                "seat-number": 66,
                "seat-type": "MB"
            },
            "train-id": 20641,
            "train-name": "CBE Vande Bharat",
            "section-name": "A"
        },
        "first-name": "Sam",
        "last-name": "Smith"
    },
    "success-message": "Request succeeded",
    "error-message": ""
}
```
###  Get details of the ticket receipt for the passenger

> Retrieves a ticket for a passenger based on the train ID and passenger email.

Endpoint

```http
GET /api/ticket/receipt?train-id=<train-id>&email=<email>
```
Parameters


| Parameter      |   Type |Description                                           |
| -------------- | ------------| ----------------------------------------------------- |
| `train-id`     |   `Integer` | The ID of the train associated with the ticket|
| `email`  | `String`  | The email of the passenger |

Example output

```json
{
    "success": true,
    "data": {
        "email": "sam.smith@railreserve.com",
        "ticket": {
            "id": "17ac03d3-8eaa-4434-bd91-d585f2b6404a",
            "from": "bangalore",
            "to": "coimbatore",
            "cost": 30,
            "seat": {
                "seat-number": 66,
                "seat-type": "MB"
            },
            "train-id": 20641,
            "train-name": "CBE Vande Bharat",
            "section-name": "A"
        },
        "first-name": "Sam",
        "last-name": "Smith"
    },
    "success-message": "Request succeeded",
    "error-message": ""
}
```
### Modify passenger seat based on passenger seat preference

> Updates a ticket based on passenger preference and availability. The passenger can opt for a seat preference. Accepted seat preferences include `LB`, `MB`, `UB`, `SLB`, `SUB`. System updates the seat based on seat availability.

Endpoint

```http
PATCH /api/ticket/update
```

Example of body

```json
{
    "ticket-id": "17ac03d3-8eaa-4434-bd91-d585f2b6404a",
    "train-id": 20641,
    "email": "sam.smith@railreserve.com",
    "preference": "SLB"
}
```

Parameters


| Parameter      | Type | Description                                           |
| -------------- | --------|----------------------------------------------------- |
| `ticket-id` | `String` |The ID of the ticket associated with the train             |
| `train-id`   | `Integer`      | The ID of the train associated with the ticket|
| `email`  | `String`    | The email of the passenger |
| `preference` | `String`      | Seat preference by the passenger. Accepted seat preferences include `LB`, `MB`, `UB`, `SLB`, `SUB`|

Example output

```json
{
    "success": true,
    "data": {
        "email": "sam.smith@railreserve.com",
        "ticket": {
            "id": "17ac03d3-8eaa-4434-bd91-d585f2b6404a",
            "from": "bangalore",
            "to": "coimbatore",
            "cost": 30,
            "seat": {
                "seat-number": 47,
                "seat-type": "SLB"
            },
            "train-id": 20641,
            "train-name": "CBE Vande Bharat",
            "section-name": "A"
        },
        "first-name": "Sam",
        "last-name": "Smith"
    },
    "success-message": "Request succeeded",
    "error-message": ""
}
```
### Get passengers and seat they are allocated by the requested section 

> Retrieves a list of passengers for a specific train and section.

Endpoint

```http
GET /api/trains/filter?id=<id>&section=<section>
```

Parameters


| Parameter      | Type | Description                                           |
| -------------- |  ------- | ----------------------------------------------------- |       
| `id`         | `Integer` | The Id of the train|
| `section`      | `Character` | The section of the train to be filtered. Accepted value include `A` or `B` |

Example output

```json
{
  "success": true,
  "data": {
    "id": 20641,
    "name": "CBE Vande Bharat",
    "from": "bangalore",
    "to": "coimbatore",
    "cost": 30,
    "sections": [
      {
        "name": "A",
        "capacity": 72,
        "passengers": [
          {
            "email": "arjun.s@railreserve.com",
            "ticket": {
              "id": "40280fc9-c611-4283-8fdb-0c5f0075e90d",
              "from": "bangalore",
              "to": "coimbatore",
              "cost": 30,
              "seat": {
                "seat-number": 1,
                "seat-type": "LB"
              },
              "train-id": 20641,
              "train-name": "CBE Vande Bharat",
              "section-name": "A"
            },
            "first-name": "Arjun",
            "last-name": "Surya"
          },
          {
            "email": "gaurav.a@railreserve.com",
            "ticket": {
              "id": "e51b5048-44d5-4397-83e7-8532b12e5ca2",
              "from": "bangalore",
              "to": "coimbatore",
              "cost": 30,
              "seat": {
                "seat-number": 2,
                "seat-type": "MB"
              },
              "train-id": 20641,
              "train-name": "CBE Vande Bharat",
              "section-name": "A"
            },
            "first-name": "Gaurav",
            "last-name": "Agarwal"
          },
          {
            "email": "ritvik.a@railreserve.com",
            "ticket": {
              "id": "cfb5b724-bda6-467a-97f5-3df9f79fe2f3",
              "from": "bangalore",
              "to": "coimbatore",
              "cost": 30,
              "seat": {
                "seat-number": 3,
                "seat-type": "UB"
              },
              "train-id": 20641,
              "train-name": "CBE Vande Bharat",
              "section-name": "A"
            },
            "first-name": "Ritvik",
            "last-name": "Aryendra"
          },
          {
            "email": "sam.smith@railreserve.com",
            "ticket": {
              "id": "17ac03d3-8eaa-4434-bd91-d585f2b6404a",
              "from": "bangalore",
              "to": "coimbatore",
              "cost": 30,
              "seat": {
                "seat-number": 47,
                "seat-type": "SLB"
              },
              "train-id": 20641,
              "train-name": "CBE Vande Bharat",
              "section-name": "A"
            },
            "first-name": "Sam",
            "last-name": "Smith"
          }
        ],
        "available-seats": 68
      }
    ]
  },
  "success-message": "Request succeeded",
  "error-message": ""
}
```
### Cancel a ticket and remove the passenger from the train

> Cancels a ticket for a passenger and removes the associated passenger from the train.

Endpoint
```http
DELETE /api/ticket/cancel?train-id=<train-id>&email=<email>
```

Parameters


| Parameter      | Type | Description                                           |
| -------------- |  --- | ----------------------------------------------------- |       
| `id` | `Integer`        | The Id of the train|
| `email` | `String`      | The email of the passenger whose ticket to be cancelled|

Example output

```json
{
    "success": true,
    "data": {
        "deleted-objects": 1
    },
    "success-message": "Request succeeded",
    "error-message": ""
}
```
### Get the standard seating plan structure for all trains.

Endpoint

```http
GET /api/trains/seat-plan
```

Example output
```json
{
    "success": true,
    "data": {
        "1": "LB",
        "2": "MB",
        "3": "UB",
        "4": "LB",
        "5": "MB",
        "6": "UB",
        "7": "SLB",
        "8": "SUB",
        "9": "LB",
        "10": "MB",
        "11": "UB",
        "12": "LB",
        "13": "MB",
        "14": "UB",
        "15": "SLB",
        "16": "SUB",
        "17": "LB",
        "18": "MB",
        "19": "UB",
        "20": "LB",
        "21": "MB",
        "22": "UB",
        "23": "SLB",
        "24": "SUB",
        "25": "LB",
        "26": "MB",
        "27": "UB",
        "28": "LB",
        "29": "MB",
        "30": "UB",
        "31": "SLB",
        "32": "SUB",
        "33": "LB",
        "34": "MB",
        "35": "UB",
        "36": "LB",
        "37": "MB",
        "38": "UB",
        "39": "SLB",
        "40": "SUB",
        "41": "LB",
        "42": "MB",
        "43": "UB",
        "44": "LB",
        "45": "MB",
        "46": "UB",
        "47": "SLB",
        "48": "SUB",
        "49": "LB",
        "50": "MB",
        "51": "UB",
        "52": "LB",
        "53": "MB",
        "54": "UB",
        "55": "SLB",
        "56": "SUB",
        "57": "LB",
        "58": "MB",
        "59": "UB",
        "60": "LB",
        "61": "MB",
        "62": "UB",
        "63": "SLB",
        "64": "SUB",
        "65": "LB",
        "66": "MB",
        "67": "UB",
        "68": "LB",
        "69": "MB",
        "70": "UB",
        "71": "SLB",
        "72": "SUB"
    },
    "success-message": "Request succeeded",
    "error-message": ""
}
```

### Get list of trains with corresponding sections and passengers

Endpoint

```http
GET /api/trains
```

Example output

```json
{
    "success": true,
    "data": [
        {
            "id": 22637,
            "name": "West Coast Express",
            "from": "chennai",
            "to": "mangalore",
            "cost": 20,
            "sections": [
                {
                    "name": "A",
                    "capacity": 72,
                    "passengers": [
                        {
                            "email": "ganesh.s@railreserve.com",
                            "ticket": {
                                "id": "3d1a9c8d-edc7-40a8-9c8f-4fa072860ce4",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 1,
                                    "seat-type": "LB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "A"
                            },
                            "first-name": "Ganesh",
                            "last-name": "Shanker"
                        },
                        {
                            "email": "raj.p@railreserve.com",
                            "ticket": {
                                "id": "0cea871f-c0f2-4c06-9921-e6c49b8b11f0",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 2,
                                    "seat-type": "MB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "A"
                            },
                            "first-name": "Raj",
                            "last-name": "P"
                        },
                        {
                            "email": "suraj.s@railreserve.com",
                            "ticket": {
                                "id": "d2eda1fe-03c2-4ab6-8fab-5561db768773",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 3,
                                    "seat-type": "UB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "A"
                            },
                            "first-name": "Suraj",
                            "last-name": "Sharma"
                        }
                    ],
                    "available-seats": 69
                },
                {
                    "name": "B",
                    "capacity": 72,
                    "passengers": [
                        {
                            "email": "supriya.ps@railreserve.com",
                            "ticket": {
                                "id": "fd065a26-aa79-454f-9f7e-a0eba5139048",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 1,
                                    "seat-type": "LB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "B"
                            },
                            "first-name": "Supriya",
                            "last-name": "P S"
                        },
                        {
                            "email": "dheep.anirudh@railreserve.com",
                            "ticket": {
                                "id": "79554830-9cf3-488d-bccc-659456f36ad7",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 2,
                                    "seat-type": "MB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "B"
                            },
                            "first-name": "Anirudh",
                            "last-name": "Dheep"
                        },
                        {
                            "email": "sahlin.s@railreserve.com",
                            "ticket": {
                                "id": "0162375a-7c09-4e81-af28-a3ca71632fe5",
                                "from": "chennai",
                                "to": "mangalore",
                                "cost": 20,
                                "seat": {
                                    "seat-number": 3,
                                    "seat-type": "UB"
                                },
                                "train-id": 22637,
                                "train-name": "West Coast Express",
                                "section-name": "B"
                            },
                            "first-name": "Sahlin",
                            "last-name": "Ahmed"
                        }
                    ],
                    "available-seats": 69
                }
            ]
        },
        {
            "id": 20641,
            "name": "CBE Vande Bharat",
            "from": "bangalore",
            "to": "coimbatore",
            "cost": 30,
            "sections": [
                {
                    "name": "A",
                    "capacity": 72,
                    "passengers": [
                        {
                            "email": "arjun.s@railreserve.com",
                            "ticket": {
                                "id": "40280fc9-c611-4283-8fdb-0c5f0075e90d",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 1,
                                    "seat-type": "LB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "A"
                            },
                            "first-name": "Arjun",
                            "last-name": "Surya"
                        },
                        {
                            "email": "gaurav.a@railreserve.com",
                            "ticket": {
                                "id": "e51b5048-44d5-4397-83e7-8532b12e5ca2",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 2,
                                    "seat-type": "MB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "A"
                            },
                            "first-name": "Gaurav",
                            "last-name": "Agarwal"
                        },
                        {
                            "email": "ritvik.a@railreserve.com",
                            "ticket": {
                                "id": "cfb5b724-bda6-467a-97f5-3df9f79fe2f3",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 3,
                                    "seat-type": "UB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "A"
                            },
                            "first-name": "Ritvik",
                            "last-name": "Aryendra"
                        },
                        {
                            "email": "sam.smith@railreserve.com",
                            "ticket": {
                                "id": "603fb4e6-87aa-471b-99d9-2cf3a878a8e7",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 14,
                                    "seat-type": "UB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "A"
                            },
                            "first-name": "Sam",
                            "last-name": "Smith"
                        },
                        {
                            "email": "udaykumar@railreserve.com",
                            "ticket": {
                                "id": "7702f88b-67fd-454c-8a41-0a6bc415cde7",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 40,
                                    "seat-type": "SUB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "A"
                            },
                            "first-name": "uday",
                            "last-name": "kumar"
                        }
                    ],
                    "available-seats": 67
                },
                {
                    "name": "B",
                    "capacity": 72,
                    "passengers": [
                        {
                            "email": "ishani.c@railreserve.com",
                            "ticket": {
                                "id": "8f3ee8d4-60da-44e7-8d05-6ef762a69390",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 1,
                                    "seat-type": "LB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "B"
                            },
                            "first-name": "Ishani",
                            "last-name": "Chatterjee"
                        },
                        {
                            "email": "sita.reddy@railreserve.com",
                            "ticket": {
                                "id": "79788beb-4cbd-4449-9522-fe7cde8c33a6",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 2,
                                    "seat-type": "MB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "B"
                            },
                            "first-name": "Sita",
                            "last-name": "Reddy"
                        },
                        {
                            "email": "deepak.s@railreserve.com",
                            "ticket": {
                                "id": "2e5a1a19-8bf5-414b-9330-20a4a4af91a1",
                                "from": "bangalore",
                                "to": "coimbatore",
                                "cost": 30,
                                "seat": {
                                    "seat-number": 3,
                                    "seat-type": "UB"
                                },
                                "train-id": 20641,
                                "train-name": "CBE Vande Bharat",
                                "section-name": "B"
                            },
                            "first-name": "Deepak",
                            "last-name": "Chowdhury"
                        }
                    ],
                    "available-seats": 69
                }
            ]
        }
    ],
    "success-message": "Request succeeded",
    "error-message": ""
}
```

## Error Handling
The API provides relevant HTTP status codes and messages in the event of errors. The response also follows a common structure as given below.
```json
{
    "success": true,
    "data": "",
    "success-message": "Request succeeded",
    "error-message": ""
}
```
