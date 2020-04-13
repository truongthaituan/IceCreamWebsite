import { Injectable } from '@angular/core';
import { Recipe } from './recipe.model';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../api-service/api.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  recipe: Recipe;
  recipes: Recipe[];
  constructor(private apiService: ApiService) { }

  getRecipeList(): Observable<Recipe[]> {
    return this.apiService.get("/recipes").pipe(map(data => data));
  }
  getRecipeById(_id: number){
    return this.apiService.get("/recipes"  + `/${_id}`);
  }
  updateRecipe(recipe: Recipe){
    return this.apiService.put("/recipes"+ `/${recipe.id}`, recipe);
  }
  createRecipe(body:any){
    return this.apiService.post("/recipes",body).pipe(map(data => data));
  }
  deleteRecipe(recipeId: number) {
    return this.apiService.delete("/recipes" + `/${recipeId}`).pipe(map(data => data))
  }
}
