// @ts-ignore

import {Component, inject, signal} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatOption, MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {FlexLayoutServerModule} from '@angular/flex-layout/server';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatChipEditedEvent, MatChipGrid, MatChipInput, MatChipInputEvent, MatChipRow} from '@angular/material/chips';
import {MatIcon} from '@angular/material/icon';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {COMMA, ENTER} from '@angular/cdk/keycodes';

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
    MatButton,
    MatChipGrid,
    MatChipRow,
    MatIcon,
    MatChipInput
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

  readonly addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  readonly announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our tags
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);
      if (index >= 0) {
        this.tags.splice(index, 1);
      }
  }
}
