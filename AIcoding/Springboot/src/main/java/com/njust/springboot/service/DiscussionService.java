package com.njust.springboot.service;

import com.njust.springboot.entity.Discussion;
import java.util.List;

public interface DiscussionService {
    int addDiscussion(Discussion discussion);
    int deleteDiscussion(Long id);
    int updateDiscussion(Discussion discussion);
    Discussion getDiscussionById(Long id);
    List<Discussion> getAllDiscussions();
} 