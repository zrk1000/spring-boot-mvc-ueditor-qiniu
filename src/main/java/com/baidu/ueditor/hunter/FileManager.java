package com.baidu.ueditor.hunter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

public class FileManager {

//	private String dir = null;
//	private String rootPath = null;
//	private String[] allowFiles = null;
	private int count = 0;
	
	public static String accessKey;
	public static String secretKey;
	public static String baseUrl;
	public static String bucket;
	public static String uploadDirPrefix;
	
	 
	public FileManager ( Map<String, Object> conf ) {
//
//		this.rootPath = (String)conf.get( "rootPath" );
//		this.dir = this.rootPath + (String)conf.get( "dir" );
//		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		
	}
	
	public State listFile ( int index,String marker ) {
		
		BucketManager bucketManager = new BucketManager(Auth.create(accessKey, secretKey));
		FileListing fileListing;
		State state = null;
		try {
			fileListing = bucketManager.listFiles(bucket,null,marker!=null&&!"".equals(marker)?marker:null,count,null);
			List<String> fileList = new ArrayList<String>();
			for (FileInfo fileInfo : fileListing.items) {
				fileList.add(fileInfo.key);
			}
			state = this.getState( fileList.toArray(new String[fileList.size()]) );
			state.putInfo( "start", index );
			state.putInfo( "isLast", fileListing.isEOF()+"" );
			state.putInfo( "marker", fileListing.marker );
			state.putInfo( "total", Integer.MAX_VALUE );
		} catch (QiniuException e) {
			e.printStackTrace();
			state = new BaseState( false, AppInfo.NOT_EXIST );
		}
		
//		File dir = new File( this.dir );
//
//		if ( !dir.exists() ) {
//			return new BaseState( false, AppInfo.NOT_EXIST );
//		}
//		
//		if ( !dir.isDirectory() ) {
//			return new BaseState( false, AppInfo.NOT_DIRECTORY );
//		}
//		
//		Collection<File> list = FileUtils.listFiles( dir, this.allowFiles, true );
//		
//		if ( index < 0 || index > list.size() ) {
//			state = new MultiState( true );
//		} else {
//			Object[] fileList = Arrays.copyOfRange( list.toArray(), index, index + this.count );
//			state = this.getState( fileList );
//		}
//		
//		state.putInfo( "start", index );
//		state.putInfo( "total", list.size() );
		
		return state;
		
	}
	
//	private State getState ( Object[] files ) {
//		
//		MultiState state = new MultiState( true );
//		BaseState fileState = null;
//		
//		File file = null;
//		
//		for ( Object obj : files ) {
//			if ( obj == null ) {
//				break;
//			}
//			file = (File)obj;
//			fileState = new BaseState( true );
//			fileState.putInfo( "url", PathFormat.format( this.getPath( file ) ) );
//			state.addState( fileState );
//		}
//		
//		return state;
//		
//	}
	private State getState ( String[] files ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;
		
		
		for ( String url : files ) {
			if ( url == null ) {
				break;
			}
			fileState = new BaseState( true );
			fileState.putInfo( "url", baseUrl + "/" + url );
			state.addState( fileState );
		}
		
		return state;
		
	}
	
//	private String getPath ( File file ) {
//		
//		String path = PathFormat.format( file.getAbsolutePath() );
//		
//		return path.replace( this.rootPath, "/" );
//		
//	}
//	
//	private String[] getAllowFiles ( Object fileExt ) {
//		
//		String[] exts = null;
//		String ext = null;
//		
//		if ( fileExt == null ) {
//			return new String[ 0 ];
//		}
//		
//		exts = (String[])fileExt;
//		
//		for ( int i = 0, len = exts.length; i < len; i++ ) {
//			
//			ext = exts[ i ];
//			exts[ i ] = ext.replace( ".", "" );
//			
//		}
//		
//		return exts;
//		
//	}
	
}
