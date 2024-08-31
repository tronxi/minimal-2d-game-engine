import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Project} from "../models/project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) {
  }

  retrieveProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(environment.url + "/project")
  }

  createProject(project: Project): Observable<any> {
    return this.http.post<Project>(environment.url + "/project/" + project.name, {})
  }


}
