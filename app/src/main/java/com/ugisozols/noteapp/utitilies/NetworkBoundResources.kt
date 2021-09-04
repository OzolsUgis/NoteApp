package com.ugisozols.noteapp.utitilies


import com.ugisozols.noteapp.utitilies.Constants.SERVER_CONNECTION_ERROR
import kotlinx.coroutines.flow.*

// Inline function gives compiler command to insert this function in
// different block of code
inline fun <ResultType,RequestType> networkBoundResources(
// Result Type - Type that we load to/from database
// Request Type - Response from Api call
    crossinline query: () -> Flow<ResultType>,
    // Query inserts a logic how to get data from database
    crossinline fetch: suspend () -> RequestType,
    // Defines logic to get data from API
    crossinline  saveFetchResultToDatabase : suspend (RequestType) -> Unit,
    // Takes data from api (RequestType) and inserts it in Database
    crossinline onFetchFailed: (Throwable) -> Unit = {},
    // Deals if there was an error while fetching
    crossinline shouldFetch: (ResultType) -> Boolean = {true}
    // Logic when we want to fetch data from api or only take from database
) = flow{
    // First we want to emit simple Resource.Loading state with null data,
    // later on we can observe this flow as a live data
    emit(Resource.Loading(null))

    val data = query().first()
    // Takes data from database, returns the first Flow witch is List
    // Data is Result Type so we can now pass data to should fetch
    val flow = if(shouldFetch(data)){
        // Now we can emit Resource.Loading state with data attached
        emit(Resource.Loading(data))
        try {
            // In try and catch block we initiate fetch function
            val fetchedResult = fetch()
            //We saved fetch function result in fetchedResult in value
            saveFetchResultToDatabase(fetchedResult)
            // and saved result we pass in saveFetchResultToDatabase function so we can
            // add responses from API to Database
            query().map { Resource.Success(it) }
            //Query loads the new data from database and loads it into Resource.Success state
        }catch (t: Throwable){
            // Implements what happens when fetch functions leads to Throwable
            onFetchFailed(t)
            // if fetch failed we still load data from database
            query().map{
                Resource.Error(SERVER_CONNECTION_ERROR, it)
            }
        }

    }else{
        // if we do not want to fetch data from api we still load data form database
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}