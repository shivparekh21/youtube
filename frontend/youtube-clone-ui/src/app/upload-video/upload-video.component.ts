import { Component } from '@angular/core';
import {NgxFileDropEntry, FileSystemFileEntry, FileSystemDirectoryEntry, NgxFileDropModule} from 'ngx-file-drop';
import { CommonModule } from '@angular/common';
import {MatButton} from '@angular/material/button';
import {VideoService} from '../video.service';

@Component({
  selector: 'app-upload-video',
  standalone: true,
  imports: [
    CommonModule,
    NgxFileDropModule,
    MatButton
  ],
  templateUrl: './upload-video.component.html',
  styleUrl: './upload-video.component.css'
})


export class UploadVideoComponent {
  public files: NgxFileDropEntry[] = [];
  fileUploaded: boolean = false;
  fileEntry: FileSystemFileEntry | undefined;

  constructor(private videoService: VideoService){
  }

  public dropped(files: NgxFileDropEntry[]) {
    this.files = files;
    for (const droppedFile of files) {

      // Is it a file?
      if (droppedFile.fileEntry.isFile) {
        this.fileEntry = droppedFile.fileEntry as FileSystemFileEntry;  //as it need to recognize as file and not directory
        this.fileEntry.file((file: File) => {    //allows access file() method arent available on directory get name,size, type

          // Here you can access the real file
          console.log(droppedFile.relativePath, file);

          this.fileUploaded = true;
          /**
           // You could upload it like this:
           const formData = new FormData()
           formData.append('logo', file, relativePath)

           // Headers
           const headers = new HttpHeaders({
           'security-token': 'mytoken'
           })

           this.http.post('https://mybackend.com/api/upload/sanitize-and-save-logo', formData, { headers: headers, responseType: 'blob' })
           .subscribe(data => {
           // Sanitized logo returned from backend
           })
           **/

        });
      } else {
        // It was a directory (empty directories are added, otherwise only files)
        const fileEntry = droppedFile.fileEntry as FileSystemDirectoryEntry;
        console.log(droppedFile.relativePath, fileEntry);
      }
    }
  }

  public fileOver(event: any){
    console.log(event);
  }

  public fileLeave(event: any){
    console.log(event);
  }

  //Upload video to backend, we need service class which will make http calls to backend
  uploadVideo() {
    if(this.fileEntry != undefined){
      console.log(this.fileEntry);

      this.fileEntry.file( file => {
        this.videoService.uploadVideo(file).subscribe( data => {
          console.log("Video Uploaded successfully")
        })
      })
    }
  }
}
