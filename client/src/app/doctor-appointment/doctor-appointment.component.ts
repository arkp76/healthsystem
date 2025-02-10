import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { HttpService } from '../../services/http.service';

@Component({
  selector: 'app-doctor-appointment',
  templateUrl: './doctor-appointment.component.html',
  styleUrls: ['./doctor-appointment.component.scss']
})
export class DoctorAppointmentComponent implements OnInit{

  appointmentList: any[] = [];
  errorMessage: any;

  constructor(
    public httpService: HttpService,
    private authService: AuthService
    ){
    // Injects the HttpService for making API calls
  }

  ngOnInit(): void {
    // Calls getAppointments() to fetch appointment data when the component is initialized
    this.getAppointments();
  }

  getAppointments() {
    // Fetches the userId from localStorage
    const userIdString = localStorage.getItem('userId');
    const userId = userIdString ? parseInt(userIdString, 10) : null;

    if (userId !== null && !isNaN(userId)) {
      // Calls httpService.getAppointmentByDoctor(userId) to fetch the appointments
      this.httpService.getAppointmentByDoctor(userId).subscribe(
        data => {
          // Updates appointmentList with the fetched data and logs it to the console
          this.appointmentList = data;
          console.log(this.appointmentList);
        },
        error => {
          console.error('Error fetching appointments:', error);
        }
      );
    } else {
      console.error('User ID not found or invalid in localStorage.');
    }
  }
}


// import { Component, OnInit } from '@angular/core';
// import { AuthService } from '../../services/auth.service';
// import { HttpService } from '../../services/http.service';

// @Component({
//   selector: 'app-doctor-appointment',
//   templateUrl: './doctor-appointment.component.html',
//   styleUrls: ['./doctor-appointment.component.scss']
// })
// export class DoctorAppointmentComponent implements OnInit{
 
//   appointmentList: any[] = [];
//   errorMessage: any;

//   constructor(
//     public httpService: HttpService,
//     private authService: AuthService
//   ) {}

//   ngOnInit(): void {
//     this.getAppointments();
//   }

//   getAppointments() {
//     const doctorId = this.authService.getUserId();

//     if (doctorId !== null) {
//       this.httpService.getAppointmentByDoctor(doctorId).subscribe(
//         data => {
//           this.appointmentList = data;
//           console.log(this.appointmentList);
//         },
//         error => {
//           console.error('Error fetching appointments:', error);
//           this.errorMessage = 'Failed to load appointments. Please try again later.';
//         }
//       );
//     } else {
//       console.error('User ID not found or invalid.');
//       this.errorMessage = 'You are not logged in. Please log in to view your appointments.';
//     }
//   }
// }
