package com.martianlab.data.sources.backend

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.martianlab.recipes.domain.api.BackendApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.Result
import com.martianlab.recipes.tools.backend.dto.CategoryDTO
import com.martianlab.recipes.tools.backend.dto.RecipeCookingDTO
import com.martianlab.recipes.tools.backend.dto.RecipeDTO
import com.martianlab.recipes.tools.backend.dto.RecipeIngredientDTO
import com.martianlab.recipes.tools.backend.dto.response.RecipeSearchResponseBodyDTO
import com.martianlab.recipes.tools.backend.dto.response.UtkerrorDTO
import com.martianlab.recipes.tools.backend.dto.response.UtkoresponseDTO
import com.martianlab.recipes.tools.backend.mapper.toCategory
import com.martianlab.recipes.tools.backend.mapper.toCategoryList
import com.martianlab.recipes.tools.backend.mapper.toRecipeList
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse


class BackendKtorImpl() : BackendApi{

    private val URL = "http://www.utkonos.ru/api/rest/"
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    val client: HttpClient = HttpClient{
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }
    }

    internal val apiHelper = ApiHelper(client, URL)




    data class RecipeResponseBodyDTO(

        @SerializedName("RecipeList")
        val recipeList: List<RecipeDTO>?,

        @SerializedName("RecipeCookingList")
        val recipeCookingList: List<RecipeCookingDTO>?,

        @SerializedName("Category")
        val categoryList: List<CategoryDTO>?,

        @SerializedName("Total")
        val total: Int,

        @SerializedName("ErrorList")
        val errors: Array<UtkerrorDTO>?,

        @SerializedName("RecipeIngredientList")
        val ingredientList: List<RecipeIngredientDTO>?
    )



    @Serializable
    data class Owner(
        val gists_url: String = "",
        val repos_url: String = "")


    @Serializable
    data class GitHubSearchResponse(
        val total_count: Int = 0,
        val incomplete_results: Boolean = false,
        val items: List<ItemsItem>?)

    @Serializable
    data class ItemsItem(
        val stargazers_count: Int = 0,
        val name: String = "")


    @Serializable
    data class UtkoResponseDTO<B>(
        val Head: HeadDTO,
        val Body: B
    )

    @Serializable
    data class RecipeResponseBody(

        @SerialName("Total")
        val total: Int
    )



    @Serializable
    data class HeadDTO(
        val ServerIp : String
    )

//    override suspend fun recipeSearch(
//        categoryId: Long,
//        recipeId: Long,
//        count: Int,
//        offset: Int
//    ): Result<List<Recipe>> {
//        val requestUrl = "https://api.github.com/search/repositories?q=123"
//
//        client.request<GitHubSearchResponse>(requestUrl) {
//            method = HttpMethod.Get
//            accept(ContentType.Application.Json)
//            headers {
//                append("Content-Type", "application/json")
//                append("x-mob-sgm", "000")
//                append("x-mob-method", "RecipeSearch")
//            }
//        }.also { println("KTOR::: res=" + it) }
//        return Result.Failure(0)
//    }

//    override suspend fun recipeSearch(
//        categoryId: Long,
//        recipeId: Long,
//        count: Int,
//        offset: Int
//    ): Result<List<Recipe>> {
//
//        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"2f34f026c8c16373395610b5d36e92c7\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Fri, 15 Nov 2019 09:37:08 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"$count\",\"Id\":\"\",\"CategoryId\":\"$categoryId\",\"Offset\":\"$offset\",\"Return\":{\"Cooking\":\"1\",\"Ingredient\":\"1\",\"Goods\":\"1\",\"BestRecipes\":\"\",\"Comments\":\"1\",\"Seo\":\"\"}}}"
//        val res = client.request<String>(URL) {
//            parameter("request", query)
//            method = HttpMethod.Get
//            accept(ContentType.Application.Json)
//            headers {
//                append("Content-Type", "application/json")
//                append("x-mob-sgm", "000")
//                append("x-mob-method", "RecipeSearch")
//            }
//        }//.let{ Json{ ignoreUnknownKeys = true; isLenient = true}.decodeFromString<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>(UtkoresponseDTO.serializer(RecipeSearchResponseBodyDTO.serializer()), it) }
//            .let{ nonStrictJson.decodeFromString<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>(it) }
//            .let { Result.Success(it.body.toRecipeList()) }
//         //.also { println("KTOR::: res=" + it) }
//        return res
//    }

    override suspend fun recipeSearch(
        categoryId: Long,
        recipeId: Long,
        count: Int,
        offset: Int
    ): Result<List<Recipe>> {
        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"2f34f026c8c16373395610b5d36e92c7\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Fri, 15 Nov 2019 09:37:08 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"$count\",\"Id\":\"\",\"CategoryId\":\"$categoryId\",\"Offset\":\"$offset\",\"Return\":{\"Cooking\":\"1\",\"Ingredient\":\"1\",\"Goods\":\"1\",\"BestRecipes\":\"\",\"Comments\":\"1\",\"Seo\":\"\"}}}"
        return apiHelper.recipeSearch(
            query= query,
            methodName = "RecipeSearch",
            deviceId = "000"
        ).map { it.body.toRecipeList() }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"2f34f026c8c16373395610b5d36e92c7\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Fri, 15 Nov 2019 09:37:08 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"611\",\"Id\":\"\",\"CategoryId\":\"\",\"Offset\":\"\",\"Return\":{\"Cooking\":\"1\",\"Ingredient\":\"1\",\"Goods\":\"1\",\"BestRecipes\":\"\",\"Comments\":\"1\",\"Seo\":\"\"}}}"
        return apiHelper.recipeSearch(
            query= query,
            methodName = "RecipeSearch",
            deviceId = "000"
        ).map{
            it.body.toCategoryList()
        }.also { println("KTOR:::, catlist=" + it) }
    }

    override suspend fun getCategory(categoryId: Long): Result<Category?> {
        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"8b81a580dab497ee4b3d0acc50fa68a0\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Sat, 16 Nov 2019 08:13:24 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"\",\"Id\":\"\",\"CategoryId\":\"$categoryId\",\"Offset\":\"\",\"Return\":{\"Cooking\":\"\",\"Ingredient\":\"\",\"Goods\":\"\",\"BestRecipes\":\"\",\"Comments\":\"\",\"Seo\":\"\"}}}"
        return apiHelper.recipeSearch(
            query= query,
            methodName = "RecipeSearch",
            deviceId = "000"
        ).map{
            it.body.toCategory()
        }.also { println("KTOR:::, cat=" + it) }
    }

//    override suspend fun getCategories(): Result<List<Category>> {
//        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"2f34f026c8c16373395610b5d36e92c7\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Fri, 15 Nov 2019 09:37:08 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"611\",\"Id\":\"\",\"CategoryId\":\"\",\"Offset\":\"\",\"Return\":{\"Cooking\":\"1\",\"Ingredient\":\"1\",\"Goods\":\"1\",\"BestRecipes\":\"\",\"Comments\":\"1\",\"Seo\":\"\"}}}"
//        return client.request<Result<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>>(URL) {
//            method = HttpMethod.Get
//            parameter("request", query)
//            headers {
//                append("Content-Type", "application/json")
//                append("x-mob-sgm", "000")
//                append("x-mob-method", "RecipeSearch")
//            }
//        }.map{
//            it.body.toCategoryList()
//        }.also { println("KTOR:::, catlist=" + it) }
//    }

//    override suspend fun getCategory(categoryId: Long): Result<Category?> {
//        val query = "{\"Head\":{\"Method\":\"RecipeSearch\",\"RequestId\":\"8b81a580dab497ee4b3d0acc50fa68a0\",\"MarketingPartnerKey\":\"123123123\",\"DeviceId\":\"1572680090\",\"Created\":\"Sat, 16 Nov 2019 08:13:24 GMT\",\"Client\":\"\",\"AdvertisingId\":\"\",\"AppsFlyerId\":\"\"},\"Body\":{\"Count\":\"\",\"Id\":\"\",\"CategoryId\":\"$categoryId\",\"Offset\":\"\",\"Return\":{\"Cooking\":\"\",\"Ingredient\":\"\",\"Goods\":\"\",\"BestRecipes\":\"\",\"Comments\":\"\",\"Seo\":\"\"}}}"
//        return client.request<Result<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>>(URL) {
//            method = HttpMethod.Get
//            parameter("request", query)
//            headers {
//                append("Content-Type", "application/json")
//                append("x-mob-sgm", "000")
//                append("x-mob-method", "RecipeSearch")
//            }
//        }.map{
//            it.body.toCategory()
//        }.also { println("KTOR:::, cat=" + it) }
//    }
}

internal class ApiHelper(val client: HttpClient, val URL : String ){

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    internal suspend fun recipeSearch(
        query : String,
        methodName : String,
        deviceId : String
    ): Result<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>{
        try {
            val res = client.request<String>(URL) {
                parameter("request", query)
                method = HttpMethod.Get
                accept(ContentType.Application.Json)
                headers {
                    append("Content-Type", "application/json")
                    append("x-mob-sgm", deviceId)
                    append("x-mob-method", methodName)
                }
            }.let {
                nonStrictJson.decodeFromString<UtkoresponseDTO<RecipeSearchResponseBodyDTO>>(
                    it
                )
            }

            return Result.Success(res)
        }catch (e : java.lang.Exception){
            return Result.Failure(0)
        }

    }
}

private fun <From, To>  Result<From>.map(entityMapper: (From) -> To): Result<To> =
    when (this) {
        is Result.Success<From> -> Result.Success<To>(data?.let(entityMapper) )
        is Result.Failure -> Result.Failure(statusCode)
        is Result.NetworkError -> Result.NetworkError
    }