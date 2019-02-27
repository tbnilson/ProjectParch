import { Component, OnInit } from '@angular/core';
//import {MatSnackBar} from '@angular/material'; <- THis needs to go in the component that calls this
import {Inject} from '@angular/core';
import {MAT_SNACK_BAR_DATA} from '@angular/material';

@Component({
  selector: 'app-parch-snackbar',
  templateUrl: './parch-snackbar.component.html',
  styleUrls: ['./parch-snackbar.component.css']
})
export class ParchSnackbarComponent{
  snackbarMessage:string="Empty Message"
  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) { 
    this.snackbarMessage = data.message;
  }

}
