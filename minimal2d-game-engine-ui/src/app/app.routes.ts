import { Routes } from '@angular/router';
import {WorkspaceComponent} from "./pages/workspace-page/workspace/workspace.component";
import {ProjectComponent} from "./pages/project-page/project/project.component";

export const routes: Routes = [
  {path: "", component: WorkspaceComponent},
  {path: "project", component: ProjectComponent},
];
