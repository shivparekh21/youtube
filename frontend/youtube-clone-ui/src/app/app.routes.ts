import { Routes } from '@angular/router';
import {UploadVideoComponent} from './upload-video/upload-video.component';
import {SaveVideoDetailsComponent} from './save-video-details/save-video-details.component';
import {VideoPlayerComponent} from './video-player/video-player.component';

export const routes: Routes = [
  {
    path:'upload-video', component: UploadVideoComponent,
  },
  {
    path: 'save-video-details/:videoId', component: SaveVideoDetailsComponent,
  },
];
