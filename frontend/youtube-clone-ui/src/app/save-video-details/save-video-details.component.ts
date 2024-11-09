import { Component } from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOption, MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {FlexLayoutServerModule} from '@angular/flex-layout/server';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-save-video-details',
  standalone: true,
  imports: [
    FlexLayoutServerModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOption,
    MatInputModule,
    ReactiveFormsModule,
    MatButton
  ],
  templateUrl: './save-video-details.component.html',
  styleUrl: './save-video-details.component.css'
})


export class SaveVideoDetailsComponent {
  title: FormControl = new FormControl('');
  description: FormControl = new FormControl('');
  videoStatus: FormControl = new FormControl('');

  // creating a form group and binding it to ../../component.html
  saveVideoDetailsForm: FormGroup;
  constructor() {
    this.saveVideoDetailsForm = new FormGroup({
      title: this.title,
      description: this.description,
      videoStatus: this.videoStatus,
    })
  }
}
