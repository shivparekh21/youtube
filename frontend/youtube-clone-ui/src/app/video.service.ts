import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FileSystemFileEntry} from 'ngx-file-drop';

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  // Because you are making http request to backend
  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File){
    //http post call
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);

    return this.httpClient.post("http://localhost:8080/api/videos", formData);

  }
}
