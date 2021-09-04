package com.ugisozols.noteapp.utitilies


import com.ugisozols.noteapp.utitilies.Constants.SERVER_CONNECTION_ERROR
import kotlinx.coroutines.flow.*

inline fun <ResultType,RequestType> networkBoundResources(

    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline  saveFetchResultToDatabase : suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = {},
    crossinline shouldFetch: (ResultType) -> Boolean = {true}
) = flow{

    emit(Resource.Loading(null))

    val data = query().first()

    val flow = if(shouldFetch(data)){
        emit(Resource.Loading(data))
        try {
            val fetchedResult = fetch()
            saveFetchResultToDatabase(fetchedResult)
            query().map { Resource.Success(it) }
        }catch (t: Throwable){
            onFetchFailed(t)
            query().map{
                Resource.Error(SERVER_CONNECTION_ERROR, it)
            }
        }

    }else{
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}