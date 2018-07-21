package bank.embedded.data.neo4j.util;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;

import bank.embedded.data.neo4j.centrality.FindAllNodeAboutDegreeCentrality;
import bank.embedded.data.neo4j.centrality.FindAllNodeAboutDegreeCentrality.RelTypes;
import bank.embedded.data.neo4j.circleoftraversal.FindAllNodeDbCircleBybothwayOfstream;

public class Taversal {
	 public static enum LabelTypes implements Label
		{

		  P,C,N

		}
	 
	 //遍历担保环
	 public Stream<Path> circle(int flag,List<Long> passNodes, Node neighborNode,Node startNode) 
	    {
			if(flag==0) {
				 TraversalDescription td = FindAllNodeDbCircleBybothwayOfstream.graphDb.traversalDescription()
			                .depthFirst()
			                .relationships( FindAllNodeDbCircleBybothwayOfstream.RelTypes.ZDB, Direction.OUTGOING )
				 			.uniqueness(Uniqueness.NODE_PATH)
				 			.evaluator(
				 					Evaluators.includingDepths(1, 50))
				 			.evaluator(new Evaluator() {
								
								@Override
								public Evaluation evaluate(Path arg0) {
									if(arg0.length()==0) {
										return Evaluation.EXCLUDE_AND_CONTINUE;
									}
									boolean isNotInpassNodes=true;
									Node present=arg0.lastRelationship().getEndNode();
									if(passNodes.contains(present.getId())) {
										isNotInpassNodes=false;
									}
									
									return Evaluation.ofContinues(isNotInpassNodes);
								}
							})
				 			.evaluator(
				 					Evaluators.includeWhereEndNodeIs(startNode));   
				 return td.traverse(neighborNode).stream();
			}
			else {
				 TraversalDescription td = FindAllNodeDbCircleBybothwayOfstream.graphDb.traversalDescription()
			                .depthFirst()
			                .relationships(FindAllNodeDbCircleBybothwayOfstream.RelTypes.ZDB, Direction.INCOMING )
				 			.uniqueness(Uniqueness.NODE_PATH)
				 			.evaluator(
				 					Evaluators.includingDepths(1, 50))
				 			.evaluator(new Evaluator() {
								
								@Override
								public Evaluation evaluate(Path arg0) {
									if(arg0.length()==0) {
										return Evaluation.EXCLUDE_AND_CONTINUE;
									}
									boolean isNotInpassNodes=true;
									Node present=arg0.lastRelationship().getStartNode();
									if(passNodes.contains(present.getId())) {
										isNotInpassNodes=false;
									}
									
									return Evaluation.ofContinues(isNotInpassNodes);
								}
							})
				 			.evaluator(
				 					Evaluators.includeWhereEndNodeIs(startNode));   
				 return td.traverse(neighborNode).stream();
			}
	    }
	 //遍历度的中心性
	 public Stream<Path> degreeCentrality(Node startNode, int depth, RelTypes zdb) {
		 TraversalDescription td = FindAllNodeAboutDegreeCentrality.graphDb.traversalDescription()
	                .breadthFirst()
	                .relationships(zdb, Direction.OUTGOING )
		 			.uniqueness(Uniqueness.RELATIONSHIP_PATH)
	               
		 			.evaluator(
		 					Evaluators.toDepth(depth));   
		 return td.traverse(startNode).stream();
		
	}
	 
	 //增加新担保查询方式
		//新担保环遍历方法
	 public Stream<Path> dbcircle( GraphDatabaseService db,Node n,RelationshipType r) 
	    {
			TraversalDescription t= db.traversalDescription()
									.depthFirst()
									.uniqueness(Uniqueness.NONE)
									.relationships(r,Direction.OUTGOING)
									.evaluator(new newnodeevaluator());
	    	


	    	return t.traverse(n).stream();
	    }
	 
	 //判断
		public class newnodeevaluator implements Evaluator{
		    public newnodeevaluator() {

			}
			
			@Override
			public Evaluation evaluate(Path path) {
			
				if(path.length()<1)
					return Evaluation.EXCLUDE_AND_CONTINUE;
				
				if( path.startNode().getId()>path.endNode().getId()||!isunique(path))
					return Evaluation.EXCLUDE_AND_PRUNE;

				if(path.startNode().getId()==path.endNode().getId())
				   return Evaluation.INCLUDE_AND_PRUNE;
				
				return Evaluation.EXCLUDE_AND_CONTINUE;
			}
			
		}
		
		//判断是否重复独立
		boolean isunique(Path path){

			Iterator<Node> nodes=path.nodes().iterator();
			long lastid=path.endNode().getId();
			
			nodes.next();
			while(nodes.hasNext()){
				Node n=nodes.next();
				if(nodes.hasNext()&&n.getId()==lastid){
					return false;}
			}
			return true;
		}
	 
}
