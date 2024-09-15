import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WorkspaceStateService {

  private _workspaceStateService = new Subject();

  get state$() {
    return this._workspaceStateService.asObservable();
  }

  update() {
    this._workspaceStateService.next(null);
  }

}
