import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FileSystemFileEntry} from 'ngx-file-drop';
import {Observable} from 'rxjs';
import {UploadVideoResponse} from './upload-video/UploadVideoResponse';

@Injectable({
  providedIn: 'root'
})
export class VideoService {
  // Because you are making http request to backend
  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse>{
    //http post call to upload video
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);
    return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/videos", formData);
  }
}
